package com.darwin.dev.chatservice.core.dto;

public record SendMessageRequest(
        int clientId,
        int channelId,
        int userId,
        String message
) {
}
