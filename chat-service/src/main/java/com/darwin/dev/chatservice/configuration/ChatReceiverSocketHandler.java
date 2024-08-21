package com.darwin.dev.chatservice.configuration;

import com.darwin.dev.chatservice.core.dto.request.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.request.SendMessageRequest;
import com.darwin.dev.chatservice.core.dto.request.SubscribeChannelRequest;
import com.darwin.dev.chatservice.core.model.SimpleMessage;
import com.darwin.dev.chatservice.service.MessageService;
import com.darwin.dev.distributed.util.Parser;
import com.darwin.dev.distributed.util.RequestCst;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatReceiverSocketHandler extends TextWebSocketHandler {
    private static final String CHAT_SEND = "/chat-send";
    private static final String CHAT_RECEIVE = "/chat-receive";
    private static final String SUBSCRIBE = "/subscribe";

    private final MessageService messageService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final HashMap<String, Set<WebSocketSession>> channelSubscribers = new HashMap<>();
    private final HashMap<String, String> activeSessions = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        activeSessions.put(session.getId(), null);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        URI uri = session.getUri();
        Assert.notNull(uri, "URI must not be null");
        String path = uri.getPath();
        Assert.notNull(path, "Path must not be null");
        String query = uri.getQuery();
        Assert.notNull(query, "Query must not be null");
        Map<String, String> queryParams = Parser.parseQueryParam(query);
        int clientId = Integer.parseInt(queryParams.get(RequestCst.CLIENT_ID));
        String payload = message.getPayload();
        if (path.contains(CHAT_SEND)) handleSendRequest(payload, clientId);
        else if (path.contains(CHAT_RECEIVE)) handleReceiveRequest(session, payload, clientId);
        else if (path.contains(SUBSCRIBE)) handleSubscribeRequest(session, payload, clientId);
    }

    private void handleSendRequest(String payload, int clientId) throws IOException {
        SendMessageRequest request = objectMapper.readValue(payload, SendMessageRequest.class);
        request.setClientId(clientId);
        SimpleMessage sentMsg = SimpleMessage.from(messageService.sendMessage(request));
        Set<WebSocketSession> sessions = channelSubscribers.get(request.getRequestId());
        if (sessions == null) return;
        TextMessage msg = new TextMessage(objectMapper.writeValueAsString(sentMsg));
        for (WebSocketSession wsSession : sessions) {
            if (wsSession.isOpen()) {
                wsSession.sendMessage(msg);
            }
        }
    }

    private void handleReceiveRequest(WebSocketSession session, String payload, int clientId) throws IOException {
        GetMessagesRequest request = objectMapper.readValue(payload, GetMessagesRequest.class);
        request.setClientId(clientId);
        List<SimpleMessage> messageList = messageService.getMessages(request)
                .stream()
                .map(SimpleMessage::from)
                .toList();
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
        activeSessions.put(session.getId(), subId);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("handleTransportError", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        String channelId = activeSessions.remove(session.getId());
        Set<WebSocketSession> subscribers = channelSubscribers.get(channelId);
        if (subscribers != null) {
            subscribers.remove(session);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
