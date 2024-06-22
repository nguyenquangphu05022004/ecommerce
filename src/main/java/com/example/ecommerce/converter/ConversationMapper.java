package com.example.ecommerce.converter;

import com.example.ecommerce.domain.Conversation;
import com.example.ecommerce.domain.dto.chat.ConversationResponse;
import com.example.ecommerce.domain.dto.UserTrack;

public class ConversationMapper {

    public static ConversationResponse mapperToConversationResponse(
            Conversation conversation, String username
    ) {
        ConversationResponse conversationResponse =
                ConversationResponse.builder()
                        .conversationName(conversation.getConversationName(username))
                        .image(conversation.getImageOfConversation(username))
                        .active(
                                conversation.getUsers()
                                        .stream()
                                        .filter(user -> user.getUsername().compareTo(username) != 0)
                                        .anyMatch(user -> UserTrack
                                                .getInstance()
                                                .getMap()
                                                .get(user.getUsername()) != null)
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
