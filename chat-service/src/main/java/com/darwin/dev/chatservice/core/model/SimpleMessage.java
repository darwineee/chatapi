package com.darwin.dev.chatservice.core.model;

import lombok.Builder;

@Builder
public record SimpleMessage(
        String id,
        String message,
        long timestamp
) {
    public static SimpleMessage from(Message message) {
        return SimpleMessage.builder()
                .id(message.id())
                .message(message.message())
                .timestamp(message.timestamp())
                .build();
    }
}
