package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.repository.ChatMessageRepository;
import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.ChatMessageRequest;
import com.example.ecommerce.service.response.APIListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        ChatMessage chat = saved;
        if (request.getFileImages() != null) {
            request.getFileImages().forEach(file -> filesStorageService.saveFile(file, saved.getId(), EntityType.CHAT_MESSAGE));
            chat = chatMessageRepository.findById(saved.getId()).get();
        }
        ChatMessageDto response = mapper.toDto(chat);
        if (chatMessage.getChatMessageDestination() == ChatMessage.ChatMessageDestination.USER) {
            messageTemplate.convertAndSendToUser(String.valueOf(request.getDestinationId()), "/topic/private-message", response);
            return response;
        }
        messageTemplate.convertAndSend("/topic/group-message/" + request.getDestinationId(), response);
        return null;
    }

    @Override
    public APIListResponse<?> getListMessageByDestination(
            ChatMessageRequest request,
            int page,
            int limit
    ) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getDestinationId(), "DestinationId");
        ValidationUtils.fieldCheckNullOrEmpty(String.valueOf(request.getChatMessageDestination()), "chatMessageDestination");
        Page<ChatMessage> pageMessages = chatMessageRepository
                .findAllByUserUsernameAndDestinationIdAndChatMessageDestination(
                        SecurityUtils.username(),
                        request.getDestinationId(),
                        request.getChatMessageDestination(),
                        PageRequest.of(page, limit)
                );
        return new APIListResponse<>(
                "ok",
                "",
                1,
                HttpStatus.OK.name(),
                page,
                limit,
                pageMessages.getTotalPages(),
                mapper.toDtoList(pageMessages.getContent()));
    }
}

