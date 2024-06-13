package com.example.ecommerce.service.impl;

import com.example.ecommerce.converter.ChatMessageMapper;
import com.example.ecommerce.domain.ChatMessage;
import com.example.ecommerce.domain.Conversation;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.chat.ChatMessageRequest;
import com.example.ecommerce.domain.dto.chat.ChatMessageResponse;
import com.example.ecommerce.repository.ChatMessageRepository;
import com.example.ecommerce.repository.ConversationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    @Override
    @Transactional
        public ChatMessageResponse createMessage(ChatMessageRequest request) {
        Optional<Conversation> conversationById = conversationRepository
                .findById(request.getSenderToConversationId());
        Optional<User> userById = userRepository.findByUsername(request.getUsernameSender());
        ChatMessage chatMessage = ChatMessage
                .builder()
                .content(request.getContent())
                .userSender(userById.get())
                .senderToConversation(conversationById.get())
                .build();
        return ChatMessageMapper.mappertoChatMessageResponse(
                chatMessageRepository.save(chatMessage),
                userById.get().getUsername()
        );

    }

    @Override
    public List<ChatMessageResponse> getListMessageByConversationId(
            Long conversationId, String username
    ) {
        List<ChatMessage> chatMessages = chatMessageRepository
                .findAllBySenderToConversationId(
                        conversationId
                );
        return chatMessages
                .stream()
                .map(chatMessage -> {
                    return ChatMessageMapper.mappertoChatMessageResponse(
                            chatMessage,
                            username
                    );
                })
                .collect(Collectors.toList());
    }
}
