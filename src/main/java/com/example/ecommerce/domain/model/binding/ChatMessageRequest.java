package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.domain.entities.BaseEntity;
import com.example.ecommerce.domain.entities.chat.ChatMessage;
import com.example.ecommerce.domain.entities.chat.ChatMessageType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class ChatMessageRequest extends BaseEntity {
    @NotNull
    private Long fromUserId;
    @NotNull
    private Long toDestinationId;
    @NotNull
    private ChatMessageType chatMessageType;
    @NotEmpty
    private String content;
    private List<MultipartFile> files;

    protected ChatMessageRequest(ChatMessage message) {
        this.fromUserId = message.getFromUserId();
        this.toDestinationId = message.getToDestinationId();
        this.chatMessageType = message.getChatMessageType();
        this.content = message.getContent();
        this.setId(message.getId());
    }
}
