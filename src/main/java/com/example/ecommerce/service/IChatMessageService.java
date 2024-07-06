package com.example.ecommerce.service;


import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.request.ChatMessageRequest;

import java.util.List;

public interface IChatMessageService {

    void createMessage(ChatMessageRequest request);

    List<ChatMessageDto> getListMessageByConversationId(Long conversationId);
}
