package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.chat.ChatMessageRequest;
import com.example.ecommerce.service.IChatMessageService;
import com.example.ecommerce.service.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final IChatMessageService chatMessageService;
    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMessage(
           @RequestParam("conversationId") Long conversationId,
           @RequestParam("message") String message,
           @RequestParam(value = "file", required = false) MultipartFile file
    ) {
         chatMessageService.createMessage(
                 new ChatMessageRequest(message, conversationId),
                 file);
    }

    @GetMapping("/messages/conversation/{conversationId}")
    @ResponseBody
    public List<ChatMessageDto> getListMessageByConversation(
            @PathVariable("conversationId") Long conversationId
    ) {
        return chatMessageService.getListMessageByConversationId(conversationId);
    }
}
