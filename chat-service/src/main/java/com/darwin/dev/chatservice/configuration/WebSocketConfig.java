package com.darwin.dev.chatservice.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatReceiverSocketHandler chatReceiverSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatReceiverSocketHandler, "/api/v1/messages/chat-send");
        registry.addHandler(chatReceiverSocketHandler, "/api/v1/messages/chat-receive");
        registry.addHandler(chatReceiverSocketHandler, "/api/v1/messages/subscribe");
    }
}
