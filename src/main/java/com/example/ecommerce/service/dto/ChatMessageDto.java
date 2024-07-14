package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.ChatMessageDestination;
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
    private Long destinationId;
    private ChatMessageDestination chatMessageDestination;
    private List<String> imageUrls;
}
