package com.darwin.dev.crmservice.core.repository;

import com.darwin.dev.distributed.crm.Channel;
import com.darwin.dev.distributed.crm.User;

import java.util.List;

public interface IUserChannelRepository {
    List<User> findAllUsersInChannel(int channelId);
    List<Channel> findAllChannelsOfUser(int userId);
    boolean insertUser(int channelId, int userId);
    boolean removeUser(int channelId, int userId);
}
