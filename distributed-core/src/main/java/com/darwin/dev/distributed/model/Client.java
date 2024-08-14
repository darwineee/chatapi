package com.darwin.dev.distributed.model;

import lombok.Builder;

@Builder
public record Client(
        int id,
        String publicId,
        String name,
        String apiKey
) {
}
