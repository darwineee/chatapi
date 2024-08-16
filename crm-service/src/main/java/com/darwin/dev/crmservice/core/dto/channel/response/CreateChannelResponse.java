package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.distributed.crm.Channel;
import lombok.Builder;

@Builder
public record CreateChannelResponse(
        int id,
        String name
) {
    public static CreateChannelResponse from(Channel channel) {
        return CreateChannelResponse.builder()
                .id(channel.id())
                .name(channel.name())
                .build();
    }
}
