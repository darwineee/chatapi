package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.distributed.crm.Channel;
import lombok.Builder;

import java.util.List;

@Builder
public record GetChannelsOfUserResponse(
        List<Channel> channels
) {
    public static GetChannelsOfUserResponse from(List<Channel> channels) {
        return GetChannelsOfUserResponse.builder()
                .channels(channels)
                .build();
    }
}
