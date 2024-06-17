package com.example.ecommerce.domain.dto.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Getter
@Setter
public class ChatMessageRequest {
    private String content;
    private String usernameSender;
    private Long senderToConversationId;
}
