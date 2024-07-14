package com.example.ecommerce.service;


import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.request.ChatMessageRequest;

import java.util.List;

public interface IChatMessageService {

    ChatMessageDto createMessage(ChatMessageRequest request);

    List<ChatMessageDto> getListMessageByDestination(ChatMessageRequest request);
}
