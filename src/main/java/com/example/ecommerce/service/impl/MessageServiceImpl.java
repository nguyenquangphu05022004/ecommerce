package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Message;
import com.example.ecommerce.domain.model.binding.FilterMessageRequest;
import com.example.ecommerce.domain.model.binding.MessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.MessageModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.repository.MessageRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static com.example.ecommerce.domain.entities.EntityType.Type.MESSAGE;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements IMessageService {

    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IFilesStorageService filesStorageService;
    private final UserRepository userRepository;

    @Override
    public APIResponse<MessageModelView> createMessage(MessageRequest request) {
        User fromUser = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        Message message = Message.builder()
                .content(request.getContent())
                .fromUser(fromUser)
                .toEntityType(request.getToEntityType())
                .watched(false)
                .build();

        messageRepository.save(message);
        if (!CollectionUtils.isEmpty(request.getFiles())) {
            message.setImages(request.getFiles()
                    .stream()
                    .map(f -> filesStorageService.saveFile(f, new EntityType(MESSAGE, message.getId())))
                    .toList());
        }
        return new APIResponse<>(200, new MessageModelView(message), null);
    }

    @Override
    public APIListResponse<MessageModelView> getMessages(FilterMessageRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getLimit());
        Page<Message> pageMessage = messageRepository.getMessageDetails(
                user.getId(),
                request.getEntityType().getEntityId(),
                request.getEntityType().getEntityType(),
                pageable
        );
        return new APIListResponse<>(
                200, request.getPage(), request.getLimit(),
                pageMessage.getTotalPages(),
                pageMessage.getContent()
                        .stream().map(s -> new MessageModelView(s))
                        .toList());
    }

    @Override
    public APIListResponse<MessageModelView> getMessageGallery(int page, int limit) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<Message> pageMessage = messageRepository.getMessageGallery(user.getId(), pageable);

        return new APIListResponse<>(
                200, page, limit, pageMessage.getTotalPages(),
                pageMessage.getContent().stream().map(MessageModelView::new)
                        .toList());
    }


}

