package com.darwin.dev.chatservice.core.service;

import com.darwin.dev.chatservice.core.dto.request.GetMessagesRequest;
import com.darwin.dev.chatservice.core.dto.request.SendMessageRequest;
import com.darwin.dev.chatservice.core.model.Message;

import java.util.List;

public interface IMessageService {
    List<Message> getMessages(GetMessagesRequest request);
    Message sendMessage(SendMessageRequest request);
}
