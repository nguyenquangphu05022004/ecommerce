package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.Basket;
import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.domain.Conversation;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.ChatMessageRequest;
import jakarta.mail.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("chatMessageMapper")
public class ChatMessageMapper extends ImageMapper implements
        IMapper<ChatMessage, ChatMessageRequest, ChatMessageDto> {
    @Override
    public ChatMessage toEntity(ChatMessageRequest chatMessageRequest) {
        ChatMessage chatMessage = ChatMessage.builder()
                .user(User.builder().id(chatMessageRequest.getId()).build())
                .content(chatMessageRequest.getContent())
                .conversation(Conversation.builder().id(chatMessageRequest.getConversationId()).build())
                .build();
        return chatMessage;
    }

    @Override
    public ChatMessageDto toDto(ChatMessage chatMessage) {
        ChatMessageDto message = ChatMessageDto.builder()
                .conversationId(chatMessage.getConversation().getId())
                .content(chatMessage.getContent())
                .createdBy(chatMessage.getCreatedBy())
                .modifiedDate(chatMessage.getModifiedDate())
                .imageUrls(getImageUrl(SystemUtils.TAG, chatMessage.getImages()))
                .id(chatMessage.getId())
                .build();
        return message;
    }


    @Override
    public List<ChatMessageDto> toDtoList(Collection<? extends ChatMessage> chatMessages) {
        List<ChatMessageDto> messages = new ArrayList<>();
        if(chatMessages != null) {
            chatMessages.forEach(message -> messages.add(toDto(message)));
        }
        return messages;
    }
}
