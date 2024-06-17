package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.chat.ChatMessageRequest;
import com.example.ecommerce.domain.dto.chat.ChatMessageResponse;
import com.example.ecommerce.service.IChatMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MessageController {

    private final IChatMessageService chatMessageService;

    @MessageMapping("/chat.sendMessage/{usernameSender}/{conversationId}")
    @SendTo("/topic/public/conversation/{conversationId}")
    public ChatMessageResponse sendMessage(
            @Payload ChatMessageRequest chatMessage,
            @DestinationVariable("usernameSender") String usernameSender,
            @DestinationVariable("conversationId") Long conversationId
    ) {
        chatMessage.setUsernameSender(usernameSender);
        chatMessage.setSenderToConversationId(conversationId);
        return chatMessageService.createMessage(chatMessage);
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
