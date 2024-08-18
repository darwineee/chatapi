package com.darwin.dev.crmservice.core.service;

import com.darwin.dev.crmservice.core.dto.client.response.GetAllClientResponse;
import com.darwin.dev.crmservice.core.dto.client.response.GetClientInfoResponse;
import com.darwin.dev.crmservice.core.exception.resource.InvalidClientId;

public interface IClientService {
    GetAllClientResponse getAllClients();
    GetClientInfoResponse getClientInfo(int clientId) throws InvalidClientId;
}
