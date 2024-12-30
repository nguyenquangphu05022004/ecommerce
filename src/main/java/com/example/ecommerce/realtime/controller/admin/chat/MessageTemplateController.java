package com.example.ecommerce.realtime.controller.admin.chat;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.realtime.controller.admin.chat.vo.MessageTemplateCreateReqVO;
import com.example.ecommerce.realtime.controller.admin.chat.vo.MessageTemplateSimpleRespVO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/realtime/chat/message/templates")
public class MessageTemplateController {


    @PostMapping
    @Operation(summary = "Tao message template")
    @PreAuthorize("@ss.hasPermission('realtime-chat-message-template:update')")
    public CommonResult<Boolean> createTemplate(@RequestBody MessageTemplateCreateReqVO req) {
        return CommonResult.success(true);
    }

    @GetMapping
    @Operation(summary = "Lay danh sach")
    public CommonResult<List<MessageTemplateSimpleRespVO>> getListTemplate() {
        return null;
    }

    @PutMapping
    @PreAuthorize("@ss.hasPermission('realtime-chat-message-template:update')")
    @Operation(summary = "cap nhat template")
    public CommonResult<Boolean> updateTemplate(@RequestBody MessageTemplateCreateReqVO req) {
        return null;
    }

}
