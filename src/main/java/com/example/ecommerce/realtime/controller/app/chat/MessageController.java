package com.example.ecommerce.realtime.controller.app.chat;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageCreateReqVO;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageRespVO;
import com.example.ecommerce.realtime.controller.app.chat.vo.user.ChatUserRespVO;
import com.example.ecommerce.realtime.service.chat.ChatUserService;
import com.example.ecommerce.realtime.service.chat.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.ecommerce.frame.common.pojo.CommonResult.success;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app-api/realtime/chat/messages")
@Tag(name = "Chat Message")
public class MessageController {

    private final MessageService messageService;
    private final ChatUserService chatUserService;
    @PostMapping
    @Operation(summary = "Tao message cho user")
    public CommonResult<MessageRespVO> createMessage(@RequestBody MessageCreateReqVO req) {
        return success(new MessageRespVO(this.messageService.createMessage(req)));
    }

    @GetMapping
    @Operation(summary = "lay danh sach cuoc tro chuyen cua user hien tai")
    public CommonResult<List<ChatUserRespVO>> getAllConversation() {
        return success(chatUserService.getListChat(SecurityUtils.getLoginUserMemberId()));
    }

}
