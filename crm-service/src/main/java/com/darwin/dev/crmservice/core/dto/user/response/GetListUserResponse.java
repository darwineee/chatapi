package com.darwin.dev.crmservice.core.dto.user.response;

import com.darwin.dev.distributed.crm.User;
import lombok.Builder;

import java.util.List;

@Builder
public record GetListUserResponse(
        List<User> users
) {
    public static GetListUserResponse from(List<User> users) {
        return GetListUserResponse.builder().users(users).build();
    }
}
