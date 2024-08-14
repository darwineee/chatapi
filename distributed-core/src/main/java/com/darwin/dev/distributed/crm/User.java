package com.darwin.dev.distributed.crm;

import lombok.Builder;

@Builder
public record User(
        int id,
        int clientId,
        String name
) {
}
