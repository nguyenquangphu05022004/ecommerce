package com.example.ecommerce.realtime.controller.app.chat;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageCreateReqVO;
import com.example.ecommerce.realtime.controller.app.chat.vo.MessageRespVO;
import com.example.ecommerce.realtime.controller.app.chat.vo.user.ChatUserRespVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.Message;
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
    @PostMapping
    @Operation(summary = "Tao message cho user")
    public CommonResult<MessageRespVO> createMessage(@RequestBody MessageCreateReqVO req) {
        return success(this.messageService.createMessage(req), MessageRespVO::new);
    }

    @GetMapping
    @Operation(summary = "Lấy danh sách tin nhắn giữa 2 người dùng")
    public CommonResult<List<MessageRespVO>> getListMessageBetweenTwoUsers(@RequestParam("toUserId") Long toUserId) {
        List<Message> messages = messageService.getListMessageBetweenTwoUsers(SecurityUtils.getLoginUserMemberId(), toUserId);
        return success(CollUtils.convertList(messages, MessageRespVO::new));
    }

    @GetMapping("/count-unread-message")
    @Operation(summary = "Lấy số lượng tin nhắn chưa đọc")
    public CommonResult<Long> getTotalUnreadMessageFromUser() {
        Long totalUnreadMessage = messageService.getTotalUnreadMessageFromUserId(SecurityUtils.getLoginUserMemberId());
        return success(totalUnreadMessage);
    }




}
