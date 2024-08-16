package com.darwin.dev.crmservice.core.dto.client;

import com.darwin.dev.distributed.crm.Client;
import lombok.Builder;

@Builder
public record GetClientInfoResponse(
        int id,
        String publicId,
        String name
) {
    public static GetClientInfoResponse from(Client client) {
        return GetClientInfoResponse.builder()
                .id(client.id())
                .publicId(client.publicId())
                .name(client.name())
                .build();
    }
}
