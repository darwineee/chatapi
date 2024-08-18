package com.darwin.dev.crmservice.core.dto.channel.request;

import lombok.Builder;

@Builder
public record GetChannelRequest(
        int clientId,
        int channelId
) {
}
