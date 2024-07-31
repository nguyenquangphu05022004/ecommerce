package com.example.ecommerce.domain.model.modelviews.messages;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.chat.ChatMessageType;
import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.domain.model.binding.ChatMessageRequest;
import com.example.ecommerce.service.mapper.ImageMapper;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatMessageViewModel extends ChatMessageRequest implements ImageMapper {
    private List<String> imageUrls;
    public ChatMessageViewModel(ChatMessage chatMessage) {
        super(chatMessage);
        this.imageUrls = getImageUrl(EntityType.CHAT_MESSAGE.name(), chatMessage.getImages());
    }
}
