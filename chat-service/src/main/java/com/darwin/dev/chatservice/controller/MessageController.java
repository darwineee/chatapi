package com.darwin.dev.chatservice.controller;

import com.darwin.dev.chatservice.core.controller.IMessageController;
import com.darwin.dev.chatservice.core.dto.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.SendMessageRequest;
import com.darwin.dev.chatservice.core.model.Message;
import com.darwin.dev.chatservice.core.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class MessageController implements IMessageController {

    private final IMessageService messageService;

    @Override
    @MessageMapping("chat.receive")
    public Flux<Message> getMessages(@Payload GetMessagesRequest request) {
        return messageService.getMessages(request);
    }

    @Override
    @MessageMapping("chat.send")
    public Mono<Message> sendMessage(@Payload SendMessageRequest request) {
        return messageService.sendMessage(request);
    }
}
