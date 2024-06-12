package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.chat.ConversationResponse;

import java.util.List;

public interface IConversationService {

    ConversationResponse createConversation(List<String> usernames);

}
