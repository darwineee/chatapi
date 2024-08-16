package com.darwin.dev.crmservice.core.dto.channel.request;

public record QuitChannelRequest(
        int channelId,
        int userId
) {
}
