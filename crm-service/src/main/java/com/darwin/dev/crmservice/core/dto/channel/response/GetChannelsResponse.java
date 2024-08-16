package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.distributed.crm.Channel;
import lombok.Builder;

import java.util.List;

@Builder
public record GetChannelsResponse(
        List<Channel> channels
) {
    public static GetChannelsResponse from(List<Channel> channels) {
        return GetChannelsResponse.builder()
                .channels(channels)
                .build();
    }
}
