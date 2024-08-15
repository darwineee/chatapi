package com.darwin.dev.crmservice.repository;

import com.darwin.dev.crmservice.core.repository.IUserRepository;
import com.darwin.dev.distributed.crm.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository implements IUserRepository {
    private final JdbcClient jdbcClient;

    @Override
    public List<User> findAll(int clientId) {
        return jdbcClient.sql("select * from users where client_id = :clientId")
                .param("clientId", clientId)
                .query(User.class)
                .list();
    }

    @Override
    public Optional<User> findById(int userId) {
        return jdbcClient.sql("select * from users where id = :userId")
                .param("userId", userId)
                .query(User.class)
                .optional();
    }

    @Override
    public int save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql("insert into users (name, client_id) values(:name, :clientId)")
                .param("name", user.name())
                .param("clientId", user.clientId())
                .update(keyHolder, "id");
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
