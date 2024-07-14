package com.example.ecommerce.controller;

import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.dto.ChatMessageDto;
import com.example.ecommerce.service.request.ChatMessageRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@RestController
public class ChatController {
    private final IChatMessageService chatMessageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(
            @RequestPart("request") ChatMessageRequest request,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        request.setFileImages(files);
        ChatMessageDto response = chatMessageService.createMessage(request);
        if (response == null) {
            return ResponseEntity.ok(
                    OperationResponse.builder()
                            .success(true)
                            .message(String.format("A message is sent into group with id %s", response.getDestinationId()))
                            .build()
            );
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllByConversationId(
            @RequestBody ChatMessageRequest request
    ) {
        return ResponseEntity.ok(chatMessageService.getListMessageByDestination(request));
    }

}
