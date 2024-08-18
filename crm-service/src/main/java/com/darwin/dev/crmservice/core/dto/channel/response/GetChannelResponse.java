package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.distributed.model.crm.Channel;
import lombok.Builder;

@Builder
public record GetChannelResponse(
        int id,
        String name
) {
    public static GetChannelResponse from(Channel channel) {
        return GetChannelResponse.builder()
                .id(channel.id())
                .name(channel.name())
                .build();
    }
}
