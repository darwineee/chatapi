package com.darwin.dev.chatservice.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetMessagesRequest {
    private int clientId;
    private int channelId;
}
