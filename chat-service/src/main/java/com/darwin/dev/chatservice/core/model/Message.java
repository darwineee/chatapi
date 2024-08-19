package com.darwin.dev.chatservice.core.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "msgstore")
public record Message(
        @Id String id,
        int clientId,
        int channelId,
        int userId,
        String message
) {
}
