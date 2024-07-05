package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
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
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.common.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;
    private final SimpMessagingTemplate messageTemplate;
    private final IImageService imageService;
    @Override
    @Transactional
    public void createMessage(ChatMessageRequest request,
                                             MultipartFile file) {
        Optional<Conversation> conversationById = conversationRepository
                .findById(request.getSenderToConversationId());
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        ChatMessage chatMessage = ChatMessage
                .builder()
                .content(request.getContent())
                .userSender(user)
                .senderToConversation(conversationById.get())
                .images(new ArrayList<>())
                .build();
        if(file != null) {
            var image = imageService.uploadFile(file, SystemUtils.TAG);
            chatMessage.setImages(Arrays.asList(image));
        }
        ChatMessageResponse messageResponse = ChatMessageMapper
                .mappertoChatMessageResponse(
                        chatMessageRepository.save(chatMessage),
                        user.getUsername()
                );
        messageTemplate.convertAndSend(
                "/topic/public/conversation/" +
                        request.getSenderToConversationId(),
                messageResponse
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
        List<ChatMessageResponse> messageResponse = chatMessages
                .stream()
                .map(chatMessage -> {
                    return ChatMessageMapper.mappertoChatMessageResponse(
                            chatMessage,
                            username
                    );
                })
                .collect(Collectors.toList());
        return messageResponse;
    }
}
