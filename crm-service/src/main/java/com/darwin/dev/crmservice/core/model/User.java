package com.darwin.dev.crmservice.core.model;

import lombok.Builder;

@Builder
public record User(
        int id,
        int clientId,
        String name
) {
}
