package com.darwin.dev.crmservice.repository;

import com.darwin.dev.crmservice.core.repository.IClientRepository;
import com.darwin.dev.distributed.model.crm.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClientRepository implements IClientRepository {
    private final JdbcClient jdbcClient;

    @Override
    public List<Client> findAll() {
        return jdbcClient.sql("select * from clients")
                .query(Client.class)
                .list();
    }

    @Override
    public Optional<Client> findById(int id) {
        return jdbcClient.sql("select * from clients where id = :id")
                .param("id", id)
                .query(Client.class)
                .optional();
    }
}
