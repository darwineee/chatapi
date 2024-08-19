package com.darwin.dev.chatservice.repository;

import com.darwin.dev.chatservice.core.model.Message;
import com.darwin.dev.chatservice.core.repository.IMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessageRepository implements IMessageRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<Message> save(Message message) {
        return mongoTemplate.save(message)
                .doOnSuccess(it -> log.info("Message saved : {}", it.toString()))
                .doOnError(throwable -> log.error("Error while saving message", throwable));
    }

    @Override
    public Flux<Message> findAllBy(int clientId, int channelId) {
        Query query = new Query(Criteria
                .where("clientId").is(clientId)
                .and("channelId").is(channelId)
        );
        return mongoTemplate.query(Message.class)
                .matching(query)
                .all();
    }
}
