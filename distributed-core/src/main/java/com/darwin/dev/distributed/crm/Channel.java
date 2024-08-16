package com.darwin.dev.distributed.crm;

import lombok.Builder;

@Builder
public record Channel(
        int id,
        int clientId,
        String name
) {
}
