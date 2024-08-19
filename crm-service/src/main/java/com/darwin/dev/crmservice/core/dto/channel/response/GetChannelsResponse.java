package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.crmservice.core.model.Channel;
import lombok.Builder;

import java.util.List;

@Builder
public record GetChannelsResponse(
        List<GetChannelResponse> channels
) {
    public static GetChannelsResponse from(List<Channel> channels) {
        return GetChannelsResponse.builder()
                .channels(channels.stream().map(GetChannelResponse::from).toList())
                .build();
    }
}
