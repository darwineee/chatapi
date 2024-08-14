package com.darwin.dev.crmservice.repository;

import com.darwin.dev.crmservice.core.repository.IClientRepository;
import com.darwin.dev.distributed.crm.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
