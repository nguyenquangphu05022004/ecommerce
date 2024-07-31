package com.example.ecommerce.controller;


import com.example.ecommerce.domain.model.binding.ChatMessageRequest;
import com.example.ecommerce.domain.model.modelviews.messages.ChatMessageViewModel;
import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@RestController
@CrossOrigin("*")
public class ChatController {
    private final IChatMessageService chatMessageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(
            @RequestPart("request") ChatMessageRequest request,
            @RequestPart(value = "files", required = false) List<MultipartFile> files
    ) {
        request.setFiles(files);
        ChatMessageViewModel message = chatMessageService.createMessage(request);
        if(message == null) {
            return ResponseEntity.ok(new OperationResponse(
                    true,
                    "send message to group"
            ));
        }
        return ResponseEntity.ok(message);
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getAllByConversationId(
            @RequestBody ChatMessageRequest request,
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit
    ) {
        return ResponseEntity.ok(chatMessageService.getMessages(request, page, limit));
    }

}
