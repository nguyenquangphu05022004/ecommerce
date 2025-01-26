package com.example.ecommerce.realtime.controller.app.chat.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
@Schema(description = "Chat two user - Message Request")
public class MessageCreateReqVO {


    @Schema(description = "send from user", example = "100")
    private Long fromUserId = SecurityUtils.getLoginUserMemberId();

    @Schema(description = "user receive message", example = "100")
    private Long toUserId;

    @NotEmpty
    private String content;

    @Schema(description = "reply message", example = "200")
    private Long replyMessageId;

    private Map<String, Object> templateParams;
    private Long messageTemplateId;

}
