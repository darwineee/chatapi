package com.darwin.dev.chatservice.core.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscribeChannelRequest {
    private int clientId;
    private int channelId;

    public String getRequestId() {
        return clientId + "-" + channelId;
    }
}
