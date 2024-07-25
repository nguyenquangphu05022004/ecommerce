package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.ChatMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ChatMessageDto extends BaseDto {
    private String content;
    private Long destinationId; //destination is userId or conversationId;
    private ChatMessage.ChatMessageDestination chatMessageDestination;
    private List<String> imageUrls;
}
