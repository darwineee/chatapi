package com.darwin.dev.crmservice.core.dto.user;

public record CreateUserRequest(
    int clientId,
    String name
) {
}
