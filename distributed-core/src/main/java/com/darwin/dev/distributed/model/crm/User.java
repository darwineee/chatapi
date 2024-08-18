package com.darwin.dev.distributed.model.crm;

import lombok.Builder;

@Builder
public record User(
        int id,
        int clientId,
        String name
) {
}
