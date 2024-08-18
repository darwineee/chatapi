package com.darwin.dev.crmservice.core.dto.user.response;

import com.darwin.dev.distributed.model.crm.User;
import lombok.Builder;

@Builder
public record CreateUserResponse(
        int id,
        int clientId,
        String name
) {
    public static CreateUserResponse from(User user) {
        return CreateUserResponse.builder()
                .id(user.id())
                .clientId(user.clientId())
                .name(user.name())
                .build();
    }
}
