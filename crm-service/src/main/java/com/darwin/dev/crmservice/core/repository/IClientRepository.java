package com.darwin.dev.crmservice.core.repository;

import com.darwin.dev.distributed.model.Client;

public interface IClientRepository {
    Client findByPublicId(String id);
}
