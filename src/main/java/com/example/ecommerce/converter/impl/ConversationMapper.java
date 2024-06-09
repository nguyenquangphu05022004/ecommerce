package com.example.ecommerce.converter.impl;

import com.example.ecommerce.domain.Conversation;
import com.example.ecommerce.domain.response.ConversationResponse;

import java.util.stream.Collectors;

public class ConversationMapper {

    public static ConversationResponse mapperToConversationResponse(
            Conversation conversation, String username
    ) {
        ConversationResponse conversationResponse =
                ConversationResponse.builder()
                        .conversationName(conversation.getConversationName(username))
                        .chatMessageResponses(
                                conversation
                                        .getChatMessages()
                                        .stream()
                                        .map(message -> {
                                            return ChatMessageMapper.
                                                    mappertoChatMessageResponse(
                                                            message, username
                                                    );
                                        })
                                        .collect(Collectors.toList())
                        )
                        .build();
        conversationResponse.setId(conversation.getId());
        return conversationResponse;
    }
    public static ConversationResponse mapperToConversationResponse(
            Conversation conversation
    ) {
        ConversationResponse conversationResponse = new ConversationResponse();
        conversationResponse.setId(conversation.getId());
        return conversationResponse;
    }
}
