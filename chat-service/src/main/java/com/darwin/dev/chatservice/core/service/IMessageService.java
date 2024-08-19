package com.darwin.dev.chatservice.core.service;

import com.darwin.dev.chatservice.core.dto.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.SendMessageRequest;
import com.darwin.dev.chatservice.core.model.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMessageService {
    Flux<Message> getMessages(GetMessagesRequest request);
    Mono<Message> sendMessage(SendMessageRequest request);
}
