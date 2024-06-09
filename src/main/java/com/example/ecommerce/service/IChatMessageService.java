package com.example.ecommerce.service;

import com.example.ecommerce.domain.request.ChatMessageRequest;
import com.example.ecommerce.domain.response.ChatMessageResponse;

import java.util.List;

public interface IChatMessageService {

    ChatMessageResponse createMessage(ChatMessageRequest request);

    List<ChatMessageResponse> getListMessageByConversationId(
            Long conversationId,
            String username
    );
}
