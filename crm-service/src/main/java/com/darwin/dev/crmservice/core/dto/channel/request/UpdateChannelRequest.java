package com.darwin.dev.crmservice.core.dto.channel.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChannelRequest {
    private int clientId;
    private String name;
}
