package com.darwin.dev.crmservice.core.dto.user.response;

import com.darwin.dev.crmservice.core.model.User;
import lombok.Builder;

import java.util.List;

@Builder
public record GetUsersResponse(
        List<GetUserResponse> users
) {
    public static GetUsersResponse from(List<User> users) {
        return GetUsersResponse.builder()
                .users(users.stream().map(GetUserResponse::from).toList())
                .build();
    }
}
