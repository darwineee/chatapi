package com.darwin.dev.chatservice.core.repository;

import com.darwin.dev.chatservice.core.model.Message;

import java.util.List;

public interface IMessageRepository {
    Message save(Message message);
    List<Message> findAllBy(int clientId, int channelId);
}
