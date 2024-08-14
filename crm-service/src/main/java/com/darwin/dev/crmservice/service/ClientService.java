package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.client.GetAllClientResponse;
import com.darwin.dev.crmservice.core.repository.IClientRepository;
import com.darwin.dev.crmservice.core.service.IClientService;
import com.darwin.dev.distributed.crm.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;

    @Override
    public GetAllClientResponse getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return GetAllClientResponse.builder().clients(clients).build();
    }
}
