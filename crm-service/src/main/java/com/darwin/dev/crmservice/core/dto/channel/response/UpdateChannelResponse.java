package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.distributed.crm.Channel;
import lombok.Builder;

@Builder
public record UpdateChannelResponse(
        int id,
        String name
) {
    public static UpdateChannelResponse from(Channel channel) {
        return UpdateChannelResponse.builder()
                .id(channel.id())
                .name(channel.name())
                .build();
    }
}
