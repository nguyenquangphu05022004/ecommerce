package com.example.ecommerce.converter;

import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.dto.chat.ChatMessageResponse;
import com.example.ecommerce.utils.SystemUtils;

public class ChatMessageMapper {

    public static ChatMessageResponse mappertoChatMessageResponse(
            ChatMessage chatMessage,
            String username
    ) {
        return ChatMessageResponse.builder()
                .userSender(ChatMessageResponse.UserSender.builder()
                        .fullName(chatMessage.getUserSender().getRole() == Role.VENDOR
                                ? chatMessage.getUserSender().getVendor().getShopName()
                                : chatMessage.getUserSender()
                                .getUserContactDetails() == null ?
                                chatMessage.getUserSender().getUsername()
                                : chatMessage.getUserSender().getUserContactDetails().getFullName())
                        .image(chatMessage.getUserSender().defaultImage())
                        .username(chatMessage.getUserSender().getUsername())
                        .build())
                .conversationResponse(ConversationMapper
                        .mapperToConversationResponse(
                                chatMessage.getSenderToConversation(),
                                username
                        ))
                .updatedAt(SystemUtils.getFormatDate(
                        chatMessage.getModifiedDate(),
                        SystemUtils.VN_DATE
                ))
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
