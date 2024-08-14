package com.darwin.dev.crmservice.core.repository;

import com.darwin.dev.distributed.crm.Client;

import java.util.List;

public interface IClientRepository {
    List<Client> findAll();
}
