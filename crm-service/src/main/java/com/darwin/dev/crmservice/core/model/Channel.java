package com.darwin.dev.crmservice.core.model;

import lombok.Builder;

@Builder
public record Channel(
        int id,
        int clientId,
        String name
) {
}
