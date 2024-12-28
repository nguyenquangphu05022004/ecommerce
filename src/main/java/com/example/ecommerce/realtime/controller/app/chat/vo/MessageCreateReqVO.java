package com.example.ecommerce.realtime.controller.app.chat.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Chat two user - Message Request")
public class MessageCreateReqVO {
    @Schema(description = "send from user", example = "100")
    @NotNull(message = "fromUserId can't null")
    private Long fromUserId;
    @Schema(description = "user receive message", example = "100")
    @NotNull(message = "toUserId can't null")
    private Long toUserId;

    @NotEmpty
    private String content;

    @Schema(description = "reply message", example = "200")
    private Long replyMessageId;
}
