package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.ChatMessageDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class ChatMessageRequest extends ChatMessageDto {
    private List<MultipartFile> fileImages;
}
