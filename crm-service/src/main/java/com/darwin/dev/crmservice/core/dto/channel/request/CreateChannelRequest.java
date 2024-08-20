package com.darwin.dev.crmservice.core.dto.channel.request;

import lombok.Data;

@Data
public class CreateChannelRequest {
    private int clientId;
    private String name;
}
