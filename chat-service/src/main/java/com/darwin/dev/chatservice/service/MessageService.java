package com.darwin.dev.chatservice.service;

import com.darwin.dev.chatservice.core.dto.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.SendMessageRequest;
import com.darwin.dev.chatservice.core.model.Message;
import com.darwin.dev.chatservice.core.repository.IMessageRepository;
import com.darwin.dev.chatservice.core.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final IMessageRepository messageRepository;

    @Override
    public Flux<Message> getMessages(GetMessagesRequest request) {
        return messageRepository.findAllBy(request.clientId(), request.channelId());
    }

    @Override
    public Mono<Message> sendMessage(SendMessageRequest request) {
        Message message = Message.builder()
                .clientId(request.clientId())
                .channelId(request.channelId())
                .userId(request.userId())
                .message(request.message())
                .build();
        return messageRepository.save(message);
    }
}
