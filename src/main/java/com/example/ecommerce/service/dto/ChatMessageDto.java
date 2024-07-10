package com.example.ecommerce.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class ChatMessageDto extends BaseDto {
    private String content;
    private Long conversationId;
    private List<String> imageUrls;
}
