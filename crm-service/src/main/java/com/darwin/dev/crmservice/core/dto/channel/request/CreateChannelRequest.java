package com.darwin.dev.crmservice.core.dto.channel.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChannelRequest {
    private int clientId;
    private String name;
}
