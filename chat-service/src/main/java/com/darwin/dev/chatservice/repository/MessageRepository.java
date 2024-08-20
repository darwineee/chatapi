package com.darwin.dev.chatservice.repository;

import com.darwin.dev.chatservice.core.model.Message;
import com.darwin.dev.chatservice.core.repository.IMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MessageRepository implements IMessageRepository {
    private final MongoTemplate mongoTemplate;

    public Message save(Message message) {
        return mongoTemplate.save(message);
    }

    @Override
    public List<Message> findAllBy(int clientId, int channelId) {
        Query query = new Query(Criteria
                .where("clientId").is(clientId)
                .and("channelId").is(channelId)
        );
        return mongoTemplate.find(query, Message.class);
    }
}
