package com.darwin.dev.crmservice.core.repository;

import com.darwin.dev.distributed.model.Client;

import java.util.List;
import java.util.Optional;

public interface IClientRepository {
    List<Client> findAll();
    Optional<Client> findById(int id);
}
