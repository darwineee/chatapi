package com.darwin.dev.chatservice.core.repository;

import com.darwin.dev.chatservice.core.model.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMessageRepository {
    Mono<Message> save(Message message);
    Flux<Message> findAllBy(int clientId, int channelId);
}
