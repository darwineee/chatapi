package com.darwin.dev.crmservice.core.dto.user.request;

public record GetUserRequest(
        int userId,
        int clientId
) {
}
