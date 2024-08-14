package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
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
public class ChatMessageRequest {
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long fromUserId;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Long toDestinationId;
    @NotNull(message = InvalidMessage.NOT_NULL)
    private Boolean isUser;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    private String content;
    private List<MultipartFile> files;
}
