package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.chat.ChatMessageImage;
import com.example.ecommerce.domain.entities.file.FileEntity;
import com.example.ecommerce.domain.model.binding.ChatMessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageViewModel;
import com.example.ecommerce.repository.ChatMessageRepository;
import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.response.APIListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ecommerce.domain.entities.chat.ChatMessageType.GROUP;
import static com.example.ecommerce.domain.entities.file.FileEntityType.CHAT_MESSAGE;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements IChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final SimpMessagingTemplate template;
    private final IFilesStorageService filesStorageService;

    @Override
    public ChatMessageViewModel createMessage(ChatMessageRequest request) {
        ChatMessage chatMessage = ChatMessage.builder()
                .chatMessageType(request.getChatMessageType())
                .content(request.getContent())
                .toDestinationId(request.getToDestinationId())
                .fromUserId(request.getFromUserId())
                .build();
        ChatMessage saved = chatMessageRepository.save(chatMessage);
        List<ChatMessageImage> images = request.getFiles().stream()
                .map((file) -> {
                    FileEntity fileEntity = filesStorageService.saveFile(file, saved.getId(), CHAT_MESSAGE);
                    if (fileEntity != null) {
                        return (ChatMessageImage) fileEntity;
                    }
                    return null;
                })
                .filter((messageImage) -> messageImage != null)
                .collect(Collectors.toList());
        saved.setImages(images);
        ChatMessageViewModel chatMessageViewModel = new ChatMessageViewModel(saved);
        if(saved.getChatMessageType().equals(GROUP)) {
            template.convertAndSend(String.format("/topic/public/group/%s/message", saved.getToDestinationId()), chatMessageViewModel);
            return null;
        } else {
            template.convertAndSendToUser(saved.getToDestinationId() + "", "/user/private/message", chatMessageViewModel);
            return chatMessageViewModel;
        }
    }

    @Override
    public APIListResponse<ChatMessageViewModel> getMessages(ChatMessageRequest request,
                                                             int page, int limit) {
        Page<ChatMessage> pages = chatMessageRepository.findAllByFromUserIdAndToDestinationIdAndChatMessageType(
                request.getFromUserId(),
                request.getToDestinationId(),
                request.getChatMessageType(),
                PageRequest.of(page - 1, limit, Sort.by("modifiedDate").descending())
        );
        return new APIListResponse<>(
                "ok",
                "",
                1,
                HttpStatus.OK.value(),
                page,
                limit,
                pages.getTotalPages(),
                pages.getContent().stream().map(ChatMessageViewModel::new).toList()
        );
    }
}

