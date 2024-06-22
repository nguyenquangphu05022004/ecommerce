package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.chat.ChatMessageRequest;
import com.example.ecommerce.domain.dto.chat.ChatMessageResponse;
import com.example.ecommerce.service.IChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @GetMapping("/message/user/{username}/conversation/{conversationId}")
    @ResponseBody
    public List<ChatMessageResponse> getListMessageByConversationId(
            @PathVariable("username") String username,
            @PathVariable("conversationId") Long conversationId
    ) {
        return chatMessageService.getListMessageByConversationId(conversationId, username);
    }




}
