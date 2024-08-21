package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.chat.*;
import com.example.ecommerce.domain.entities.file.FileEntity;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.model.binding.ChatMessageRequest;
import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageConversationModelView;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageDetailModelView;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageGalleryModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.repository.ChatMessageConversationRepository;
import com.example.ecommerce.repository.ChatMessageRepository;
import com.example.ecommerce.repository.ConversationRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.domain.response.APIListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ecommerce.domain.entities.file.FileEntityType.CHAT_MESSAGE;
import static com.example.ecommerce.domain.entities.file.FileEntityType.CHAT_MESSAGE_CONVERSATION;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate template;
    private final IFilesStorageService filesStorageService;
    private final ChatMessageConversationRepository chatMessageConversationRepository;
    private final UserRepository userRepository;
    private final ConversationRepository conversationRepository;

    @Override
    public APIResponse<ChatMessageGalleryModelView> createMessage(ChatMessageRequest request) {
        User fromUser = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        if (request.getIsUser()) {
            ChatMessage chatMessage = ChatMessage.builder()
                    .content(request.getContent())
                    .toUser(User.builder().id(request.getToDestinationId()).build())
                    .fromUser(fromUser)
                    .build();
            chatMessageRepository.save(chatMessage);
            saveFile(request.getFiles(), CHAT_MESSAGE, chatMessage.getId());
        } else {
            ChatMessageConversation chatMessageConversation = ChatMessageConversation.builder()
                    .toConversation(Conversation.builder().id(request.getToDestinationId()).build())
                    .fromUser(fromUser)
                    .content(request.getContent())
                    .build();
            chatMessageConversationRepository.save(chatMessageConversation);
            saveFile(request.getFiles(), CHAT_MESSAGE_CONVERSATION, chatMessageConversation.getId());
        }
        return null;
    }


    @Override
    public APIListResponse<ChatMessageGalleryModelView> getMessages(FilterMessageRequest request,
                                                                    int page, int limit) {
        String fromUserUsername = SecurityUtils.getUsername();
        if(request.isUser()) {
            Page<ChatMessage> pages = chatMessageRepository.findAllByCreatedByAndToUserId(
                    fromUserUsername,
                    request.getDestinationId(),
                    PageRequest.of(page - 1, limit)
            );
            return new APIListResponse<>(
                    "", 0, 1, 200, page, limit, pages.getTotalPages(),
                    pages.getContent().stream().map(c -> (ChatMessageGalleryModelView)new ChatMessageDetailModelView(c)).toList()
            );
        } else {
            Page<ChatMessageConversation> pages = chatMessageConversationRepository.findAllByCreatedByAndToConversationId(
                    fromUserUsername,
                    request.getDestinationId(),
                    PageRequest.of(page - 1, limit)
            );
            return new APIListResponse<>(
                    "", 0, 1, 200, page, limit, pages.getTotalPages(),
                    pages.getContent().stream().map(c -> (ChatMessageGalleryModelView)new ChatMessageConversationModelView(c)).toList()
            );
        }
    }

    @Override
    public APIListResponse<ChatMessageGalleryModelView> getMessageGallery(int page, int limit) {
        return null;
    }

    private  List<FileEntity> saveFile(List<MultipartFile> files, FileEntityType type, Long id) {
        if (!CollectionUtils.isEmpty(files)) {
            return files.stream().map(file -> {
                        return filesStorageService.saveFile(file, id, type);
                    })
                    .toList();
        }
        return null;
    }
}

