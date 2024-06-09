package com.example.ecommerce.converter.impl;

import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.domain.response.ChatMessageResponse;
import com.example.ecommerce.utils.SystemUtils;

public class ChatMessageMapper {

    public static ChatMessageResponse mappertoChatMessageResponse(
            ChatMessage chatMessage,
            String username
    ) {
        return ChatMessageResponse.builder()
                .userSender(ChatMessageResponse.UserSender.builder()
                        .fullName(chatMessage.getUserSender()
                                .getUserContactDetails().getFullName())
                        .username(chatMessage.getUserSender().getUsername())
                        .build())
                .conversationResponseMessage(ChatMessageResponse
                        .ConversationResponseMessage
                        .builder()
                        .id(chatMessage.getSenderToConversation().getId())
                        .conversationName(chatMessage.getSenderToConversation()
                                .getConversationName(username))
                        .build())
                .updatedAt(SystemUtils.getFormatDate(chatMessage.getModifiedDate(), "dd/MM/yyyy HH:mm"))
                .content(chatMessage.getContent())
                .messageType(
                        chatMessage.getUserSender()
                                .getUsername()
                                .compareTo(username) != 0
                        ? ChatMessageResponse.MessageType.RECEIVE
                        : ChatMessageResponse.MessageType.SEND
                )
                .build();
    }
}
