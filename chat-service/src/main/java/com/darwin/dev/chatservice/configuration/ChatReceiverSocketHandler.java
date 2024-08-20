package com.darwin.dev.chatservice.configuration;

import com.darwin.dev.chatservice.core.dto.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.SendMessageRequest;
import com.darwin.dev.chatservice.core.dto.SubscribeChannelRequest;
import com.darwin.dev.chatservice.core.model.Message;
import com.darwin.dev.chatservice.service.MessageService;
import com.darwin.dev.distributed.util.RequestCst;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatReceiverSocketHandler extends TextWebSocketHandler {
    private static final String SEND_PATH = "/chat-send";
    private static final String SEND_RECEIVE = "/chat-receive";
    private static final String SUBSCRIBE = "/subscribe";

    private final MessageService messageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HashMap<String, HashSet<WebSocketSession>> channelSubscribers = new HashMap<>();
    private final HashMap<WebSocketSession, String> activeSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("afterConnectionEstablished");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        URI uri = session.getUri();
        Assert.notNull(uri, "URI must not be null");
        String path = uri.getPath();
        Assert.notNull(path, "Path must not be null");
        String query = uri.getQuery();
        Assert.notNull(query, "Query must not be null");
        Map<String, String> queryParams = parseQuery(query);
        int clientId = Integer.parseInt(queryParams.get(RequestCst.CLIENT_ID));
        String payload = message.getPayload();
        if (path.contains(SEND_PATH)) handleSendRequest(payload, clientId);
        else if (path.contains(SEND_RECEIVE)) handleGetRequest(session, payload, clientId);
        else if (path.contains(SUBSCRIBE)) handleSubscribeRequest(session, payload, clientId);
    }

    private Map<String, String> parseQuery(String query) {
        return Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .collect(Collectors.toMap(pair -> pair[0], pair -> pair.length > 1 ? pair[1] : ""));
    }

    private void handleSendRequest(String payload, int clientId) throws IOException {
        SendMessageRequest request = objectMapper.readValue(payload, SendMessageRequest.class);
        request.setClientId(clientId);
        Message sentMsg = messageService.sendMessage(request);
        HashSet<WebSocketSession> sessions = channelSubscribers.get(request.getRequestId());
        TextMessage msg = new TextMessage(objectMapper.writeValueAsString(sentMsg));
        for (WebSocketSession wsSession : sessions) {
            if (wsSession.isOpen()) {
                wsSession.sendMessage(msg);
            }
        }
    }

    private void handleGetRequest(WebSocketSession session, String payload, int clientId) throws IOException {
        GetMessagesRequest request = objectMapper.readValue(payload, GetMessagesRequest.class);
        request.setClientId(clientId);
        List<Message> messageList = messageService.getMessages(request);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(messageList)));
    }

    private void handleSubscribeRequest(WebSocketSession session, String payload, int clientId) throws IOException {
        SubscribeChannelRequest request = objectMapper.readValue(payload, SubscribeChannelRequest.class);
        request.setClientId(clientId);
        String subId = request.getRequestId();
        if (!channelSubscribers.containsKey(subId)) {
            HashSet<WebSocketSession> sessions = new HashSet<>();
            sessions.add(session);
            channelSubscribers.put(subId, sessions);
        } else {
            channelSubscribers.get(subId).add(session);
        }
        activeSessions.put(session, subId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("handleTransportError", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        if (activeSessions.containsKey(session)) {
            String channelId = activeSessions.remove(session);
            channelSubscribers.get(channelId).remove(session);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
