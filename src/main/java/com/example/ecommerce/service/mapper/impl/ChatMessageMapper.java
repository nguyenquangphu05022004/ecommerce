package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.mapper.ImageMapper;
import com.example.ecommerce.service.request.ChatMessageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("chatMessageMapper")
public class ChatMessageMapper implements ImageMapper ,
        IMapper<ChatMessage, ChatMessageRequest, ChatMessageDto> {
    @Override
    public ChatMessage toEntity(ChatMessageRequest chatMessageRequest) {
        ChatMessage chatMessage = ChatMessage.builder()
                .user(User.builder().id(chatMessageRequest.getId()).build())
                .content(chatMessageRequest.getContent())
                .destinationId(chatMessageRequest.getDestinationId())
                .chatMessageDestination(chatMessageRequest.getChatMessageDestination())
                .build();
        return chatMessage;
    }

    @Override
    public ChatMessageDto toDto(ChatMessage chatMessage) {
        ChatMessageDto message = ChatMessageDto.builder()
                .destinationId(chatMessage.getDestinationId())
                .chatMessageDestination(chatMessage.getChatMessageDestination())
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
