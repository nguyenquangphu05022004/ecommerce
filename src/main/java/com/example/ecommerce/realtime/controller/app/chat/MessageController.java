package com.example.ecommerce.realtime.controller.app.chat;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageCreateReqVO;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageRespVO;
import com.example.ecommerce.realtime.service.chat.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.ecommerce.frame.common.pojo.CommonResult.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app-api/realtime/chat/messages")
@Tag(name = "Chat Message")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @Operation(summary = "Tao message cho user")
    public CommonResult<MessageRespVO> createMessage(@RequestBody MessageCreateReqVO req) {
        return success(new MessageRespVO(this.messageService.createMessage(req)));
    }



}
