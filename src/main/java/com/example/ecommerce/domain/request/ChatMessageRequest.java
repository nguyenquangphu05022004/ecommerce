package com.example.ecommerce.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageRequest {
    private String content;
    private String usernameSender;
    private Long senderToConversationId;
}
