package com.darwin.dev.crmservice.service;

import com.darwin.dev.crmservice.core.dto.client.GetAllClientResponse;
import com.darwin.dev.crmservice.core.dto.client.GetClientInfoResponse;
import com.darwin.dev.crmservice.core.exception.InvalidClientId;
import com.darwin.dev.crmservice.core.repository.IClientRepository;
import com.darwin.dev.crmservice.core.service.IClientService;
import com.darwin.dev.distributed.crm.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {
    private final IClientRepository clientRepository;

    @Override
    public GetAllClientResponse getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return GetAllClientResponse.from(clients);
    }

    @Override
    public GetClientInfoResponse getClientInfo(int clientId) throws InvalidClientId {
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()) throw new InvalidClientId();
        return GetClientInfoResponse.from(client.get());
    }
}
