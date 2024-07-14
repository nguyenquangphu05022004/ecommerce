package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.domain.ChatMessageDestination;
import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.repository.ChatMessageRepository;
import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate messageTemplate;
    private final IFilesStorageService filesStorageService;
    @Qualifier("chatMessageMapper")
    private final IMapper<ChatMessage, ChatMessageRequest, ChatMessageDto> mapper;

    @Override
    @Transactional
    public ChatMessageDto createMessage(ChatMessageRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getContent(), "Content");
        ValidationUtils.fieldCheckNullOrEmpty(request.getDestinationId(), "DestinationId");
        ValidationUtils.fieldCheckNullOrEmpty(String.valueOf(request.getChatMessageDestination()), "chatMessageDestination");
        ChatMessage chatMessage = mapper.toEntity(request);
        ChatMessage saved = chatMessageRepository.save(chatMessage);

        if(request.getFileImages() != null) {
            request.getFileImages().forEach(file -> filesStorageService.saveFile(file, saved.getId(), EntityType.CHAT_MESSAGE));
        }
        ChatMessageDto response = mapper.toDto(saved);

        if (chatMessage.getChatMessageDestination() == ChatMessageDestination.USER) {
            messageTemplate.convertAndSendToUser(String.valueOf(request.getDestinationId()), "/topic/private-message", response);
            return response;
        } else {
            messageTemplate.convertAndSendToUser(String.valueOf(request.getDestinationId()), "/topic/group-message/" + request.getDestinationId(), response);
            return null;
        }
    }

    @Override
    public List<ChatMessageDto> getListMessageByDestination(
            ChatMessageRequest request
    ) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getDestinationId(), "DestinationId");
        ValidationUtils.fieldCheckNullOrEmpty(String.valueOf(request.getChatMessageDestination()), "chatMessageDestination");
        List<ChatMessage> messages = chatMessageRepository
                .findAllByUserUsernameAndDestinationIdAndChatMessageDestination(
                        SecurityUtils.username(),
                        request.getDestinationId(),
                        request.getChatMessageDestination()
                );
        List<ChatMessageDto> messageResponse = mapper.toDtoList(messages);
        return messageResponse;
    }
}
