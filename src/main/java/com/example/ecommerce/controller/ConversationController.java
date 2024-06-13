package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.chat.ConversationResponse;
import com.example.ecommerce.service.IConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ConversationController {
    private final IConversationService conversationService;

    @GetMapping("/conversations")
    @ResponseBody
    public ConversationResponse createConversation(
            @RequestParam("username")List<String> usernames
            ) {
        return conversationService.createConversation(usernames);
    }
}
