package com.darwin.dev.crmservice.core.dto.user;

import com.darwin.dev.distributed.crm.User;
import lombok.Builder;

@Builder
public record GetUserResponse(
        int id,
        int clientId,
        String name
) {
    public static GetUserResponse from(User user) {
        return GetUserResponse.builder()
                .id(user.id())
                .clientId(user.clientId())
                .name(user.name())
                .build();
    }
}
