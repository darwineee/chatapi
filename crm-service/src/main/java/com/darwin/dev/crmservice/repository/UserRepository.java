package com.darwin.dev.crmservice.repository;

import com.darwin.dev.crmservice.core.repository.IUserRepository;
import com.darwin.dev.distributed.crm.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final JdbcClient jdbcClient;

    @Override
    public List<User> findAll(int clientId) {
        return List.of();
    }

    @Override
    public Optional<User> findById(int userId) {
        return Optional.empty();
    }

    @Override
    public void save(User user) {

    }
}
