package com.example.ecommerce.service.impl;

import com.example.ecommerce.converter.ConversationMapper;
import com.example.ecommerce.domain.Conversation;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.chat.ConversationResponse;
import com.example.ecommerce.repository.ConversationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConversationServiceImpl implements IConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    @Override
    public ConversationResponse createConversation(List<String> usernames) {
        List<User> users = usernames
                .stream()
                .map(username -> userRepository.findByUsername(username).get())
                .collect(Collectors.toList());
        Conversation conversation = Conversation.builder()
                .users(users)
                .build();
        conversation = conversationRepository.save(conversation);
        return ConversationMapper.mapperToConversationResponse(
                conversation
        );
    }
}
