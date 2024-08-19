package com.darwin.dev.crmservice.repository;

import com.darwin.dev.crmservice.core.repository.IUserChannelRepository;
import com.darwin.dev.crmservice.core.model.Channel;
import com.darwin.dev.crmservice.core.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserChannelRepository implements IUserChannelRepository {

    private final JdbcClient jdbcClient;

    @Override
    public List<User> findAllUsersInChannel(int channelId) {
        String sql = """
                select u.id, u.client_id, u.name from users u
                join userchannel uc
                on u.id = uc.user_id
                where uc.channel_id = :channelId
                """;
        return jdbcClient.sql(sql)
                .param("channelId", channelId)
                .query(User.class)
                .list();
    }

    @Override
    public List<Channel> findAllChannelsOfUser(int userId) {
        String sql = """
                select c.id, c.client_id, c.name from channels c
                join userchannel uc
                on c.id = uc.channel_id
                where uc.user_id = :userId
                """;
        return jdbcClient.sql(sql)
                .param("userId", userId)
                .query(Channel.class)
                .list();
    }

    @Override
    public boolean insertUser(int channelId, int userId) {
        String sql = """
                insert into userchannel (user_id, channel_id)
                values(:userId, :channelId)
                """;
        int rows = jdbcClient.sql(sql)
                .param("userId", userId)
                .param("channelId", channelId)
                .update();
        return rows > 0;
    }

    @Override
    public boolean removeUser(int channelId, int userId) {
        String sql = """
                delete from userchannel
                where channel_id = :channelId and user_id = :userId
                """;
        int rows = jdbcClient.sql(sql)
                .param("channelId", channelId)
                .param("userId", userId)
                .update();
        return rows > 0;
    }
}
