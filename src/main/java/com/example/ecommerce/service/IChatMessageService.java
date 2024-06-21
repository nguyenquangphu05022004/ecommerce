package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.chat.ChatMessageRequest;
import com.example.ecommerce.domain.dto.chat.ChatMessageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IChatMessageService {

    void createMessage(ChatMessageRequest request,
                                      MultipartFile file);

    List<ChatMessageResponse> getListMessageByConversationId(
            Long conversationId,
            String username
    );
}
