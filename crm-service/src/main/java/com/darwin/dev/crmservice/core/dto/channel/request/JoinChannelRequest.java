package com.darwin.dev.crmservice.core.dto.channel.request;

public record JoinChannelRequest(
        int channelId,
        int userId
) {
}
