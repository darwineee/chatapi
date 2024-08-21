package com.darwin.dev.chatservice.service;

import com.darwin.dev.chatservice.core.dto.request.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.request.SendMessageRequest;
import com.darwin.dev.chatservice.core.model.Message;
import com.darwin.dev.chatservice.core.repository.IMessageRepository;
import com.darwin.dev.chatservice.core.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {

    private final IMessageRepository messageRepository;

    @Override
    public List<Message> getMessages(GetMessagesRequest request) {
        return messageRepository.findAllBy(request.getClientId(), request.getChannelId());
    }

    @Override
    public Message sendMessage(SendMessageRequest request) {
        Message message = Message.builder()
                .clientId(request.getClientId())
                .channelId(request.getChannelId())
                .userId(request.getUserId())
                .message(request.getMessage())
                .timestamp(Instant.now().getEpochSecond())
                .build();
        return messageRepository.save(message);
    }
}
