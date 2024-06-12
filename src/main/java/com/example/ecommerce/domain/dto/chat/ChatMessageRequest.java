package com.example.ecommerce.domain.dto.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    private String content;
    private String usernameSender;
    private Long senderToConversationId;
}
