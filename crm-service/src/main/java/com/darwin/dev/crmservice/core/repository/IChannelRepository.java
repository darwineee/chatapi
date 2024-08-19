package com.darwin.dev.crmservice.core.repository;

import com.darwin.dev.crmservice.core.model.Channel;

import java.util.List;
import java.util.Optional;

public interface IChannelRepository {
    List<Channel> findAll(int clientId);
    int save(Channel channel);
    int update(Channel channel);
    Optional<Channel> findById(int clientId, int id);
    boolean delete(int clientId, int id);
}
