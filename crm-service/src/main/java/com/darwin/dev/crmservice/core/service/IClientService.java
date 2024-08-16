package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.client.GetAllClientResponse;
import com.darwin.dev.crmservice.core.dto.client.GetClientInfoResponse;
import com.darwin.dev.crmservice.core.exception.InvalidClientId;

public interface IClientService {
    GetAllClientResponse getAllClients();
    GetClientInfoResponse getClientInfo(int clientId) throws InvalidClientId;
}
