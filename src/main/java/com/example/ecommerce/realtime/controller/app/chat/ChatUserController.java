package com.example.ecommerce.realtime.controller.app.chat;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.realtime.controller.app.chat.vo.user.ChatUserRespVO;
import com.example.ecommerce.realtime.service.chat.ChatUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/realtime/chats")
public class ChatUserController {

    private final ChatUserService chatUserService;

    @GetMapping
    @Operation(summary = "Lấy danh sách cuộc trò chuyện của người dùng")
    public CommonResult<List<ChatUserRespVO>> getListChatUser() {
        return CommonResult.success(chatUserService.getListChat(SecurityUtils.getLoginUserMemberId()));
    }

    @DeleteMapping("/{chatUserId}")
    @Operation(summary = "Xóa cuộc trò chuyện")
    public CommonResult<Boolean> deleteChatUser(@PathVariable("chatUserId") Long chatUserId) {
        chatUserService.deleteChatUser(chatUserId);
        return CommonResult.success(true);
    }
}
