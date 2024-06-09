package com.example.ecommerce.service;

import com.example.ecommerce.domain.response.ConversationResponse;

import java.util.List;

public interface IConversationService {

    ConversationResponse createConversation(List<String> usernames);

}
