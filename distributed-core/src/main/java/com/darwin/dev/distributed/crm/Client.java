package com.darwin.dev.distributed.crm;

import lombok.Builder;

@Builder
public record Client(
        int id,
        String publicId,
        String name,
        String apiKey
) {
}
