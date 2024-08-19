package com.darwin.dev.crmservice.core.dto.client.response;

import com.darwin.dev.distributed.model.Client;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAllClientResponse(
        List<Client> clients
) {
    public static GetAllClientResponse from(List<Client> clients) {
        return GetAllClientResponse.builder()
                .clients(clients)
                .build();
    }
}
