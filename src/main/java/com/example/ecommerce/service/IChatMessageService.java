package com.example.ecommerce.service;


import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.request.ChatMessageRequest;
import com.example.ecommerce.service.response.APIListResponse;

import java.util.List;

public interface IChatMessageService {

    ChatMessageDto createMessage(ChatMessageRequest request);

    APIListResponse<?> getListMessageByDestination(ChatMessageRequest request, int page, int limit);
}
