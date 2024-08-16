package com.darwin.dev.crmservice.core.dto.channel.response;

import com.darwin.dev.distributed.crm.User;
import lombok.Builder;

import java.util.List;

@Builder
public record GetUsersInChannelResponse(
        List<User> users
) {
    public static GetUsersInChannelResponse from(List<User> users) {
        return GetUsersInChannelResponse.builder()
                .users(users)
                .build();
    }
}
