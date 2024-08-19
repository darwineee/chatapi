package com.darwin.dev.chatservice.core.dto;

public record GetMessagesRequest(
        int clientId,
        int channelId
) {
}
