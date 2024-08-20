package com.darwin.dev.chatservice.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SendMessageRequest {
    private int clientId;
    private int channelId;
    private int userId;
    private String message;

    public String getRequestId() {
        return clientId + "-" + channelId;
    }
}
