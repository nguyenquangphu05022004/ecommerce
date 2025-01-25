package com.example.ecommerce.realtime.controller.app.chat;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.realtime.service.chat.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/realtime/chats")
public class ChatUserController {

    private final ChatUserService chatUserService;


}
