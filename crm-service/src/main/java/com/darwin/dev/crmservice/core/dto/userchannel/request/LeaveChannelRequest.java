package com.darwin.dev.crmservice.core.dto.userchannel.request;

import lombok.Builder;

@Builder
public record LeaveChannelRequest(
        int channelId,
        int userId
) {
}
