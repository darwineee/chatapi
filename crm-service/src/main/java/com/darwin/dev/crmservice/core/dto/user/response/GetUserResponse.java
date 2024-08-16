package com.darwin.dev.crmservice.core.dto.user.response;

import com.darwin.dev.distributed.crm.User;
import lombok.Builder;

@Builder
public record GetUserResponse(
        int id,
        String name
) {
    public static GetUserResponse from(User user) {
        return GetUserResponse.builder()
                .id(user.id())
                .name(user.name())
                .build();
    }
}
