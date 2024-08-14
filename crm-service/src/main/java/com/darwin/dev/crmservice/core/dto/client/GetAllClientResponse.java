package com.darwin.dev.crmservice.core.dto.client;

import com.darwin.dev.distributed.crm.Client;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAllClientResponse(
        List<Client> clients
) {
}
