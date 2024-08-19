package com.darwin.dev.crmservice.repository;

import com.darwin.dev.crmservice.core.repository.IChannelRepository;
import com.darwin.dev.crmservice.core.model.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChannelRepository implements IChannelRepository {

    private final JdbcClient jdbcClient;

    @Override
    public List<Channel> findAll(int clientId) {
        return jdbcClient.sql("select * from channels where client_id = :clientId")
                .param("clientId", clientId)
                .query(Channel.class)
                .list();
    }

    @Override
    public int save(Channel channel) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = """
                insert into channels (client_id, name)
                values (:clientId, :name)
                """;
        jdbcClient.sql(sql)
                .param("clientId", channel.clientId())
                .param("name", channel.name())
                .update(keyHolder, "id");
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public int update(Channel channel) {
        String sql = """
                update channels
                set name = :name
                where client_id = :clientId and id = :id
                """;
        jdbcClient.sql(sql)
                .param("id", channel.id())
                .param("clientId", channel.clientId())
                .param("name", channel.name())
                .update();
        return channel.id();
    }

    @Override
    public Optional<Channel> findById(int clientId, int id) {
        String sql = """
                select * from channels
                where client_id = :clientId and id = :id
                """;
        return jdbcClient.sql(sql)
                .param("clientId", clientId)
                .param("id", id)
                .query(Channel.class)
                .optional();
    }

    @Override
    public boolean delete(int clientId, int id) {
        String sql = """
                delete from channels
                where id = :id and client_id = :clientId
                """;
        int rows = jdbcClient.sql(sql)
                .param("clientId", clientId)
                .param("id", id)
                .update();
        return rows > 0;
    }
}
