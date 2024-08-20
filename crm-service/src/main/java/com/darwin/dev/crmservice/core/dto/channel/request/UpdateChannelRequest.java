package com.darwin.dev.crmservice.core.dto.channel.request;

import lombok.Data;

@Data
public class UpdateChannelRequest {
    private int channelId;
    private int clientId;
    private String name;
}
