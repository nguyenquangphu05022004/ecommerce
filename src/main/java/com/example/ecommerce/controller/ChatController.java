package com.example.ecommerce.controller;

import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.request.ChatMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
@RestController
public class ChatController {
    private final IChatMessageService chatMessageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(
            @RequestBody ChatMessageRequest request
    ) {
        chatMessageService.createMessage(request);
        return ResponseEntity.ok("a message was created");
    }

    @GetMapping("/messages/conversation/{conversationId}")
    public ResponseEntity<?> getAllByConversationId(
            @PathVariable("conversationId") Long id
    ) {
        return ResponseEntity.ok(chatMessageService.getListMessageByConversationId(id));
    }

}
