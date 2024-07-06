package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.repository.ChatMessageRepository;
import com.example.ecommerce.service.IChatMessageService;
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
    @Qualifier("chatMessageMapper")
    private final IMapper<ChatMessage, ChatMessageRequest, ChatMessageDto> mapper;

    @Override
    @Transactional
    public void createMessage(ChatMessageRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getContent(), "ChatMessage Content");
        ValidationUtils.fieldCheckNullOrEmpty(request.getConversationId(), "ChatMessage ConversationId");
        ChatMessage chatMessage = mapper.toEntity(request);
        ChatMessage saved = chatMessageRepository.save(chatMessage);
        messageTemplate.convertAndSend(
                "/queue/private/conversation/" +
                        request.getConversationId(),
                mapper.toDto(saved)
        );

    }

    @Override
    public List<ChatMessageDto> getListMessageByConversationId(
            Long conversationId
    ) {
        List<ChatMessage> chatMessages = chatMessageRepository
                .findAllByConversationId(
                        conversationId
                );
        List<ChatMessageDto> messageResponse = mapper.toDtoList(chatMessages);
        return messageResponse;
    }
}
