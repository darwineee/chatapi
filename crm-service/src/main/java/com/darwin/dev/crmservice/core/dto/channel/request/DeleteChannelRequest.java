package com.darwin.dev.crmservice.core.dto.channel.request;

import lombok.Builder;

@Builder
public record DeleteChannelRequest(
        int clientId,
        int channelId
) {
}
