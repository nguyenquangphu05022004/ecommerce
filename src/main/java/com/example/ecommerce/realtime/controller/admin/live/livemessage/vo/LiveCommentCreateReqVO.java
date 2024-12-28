package com.example.ecommerce.realtime.controller.admin.live.livemessage.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LiveCommentCreateReqVO {
    @NotNull(message = "live stream can't null")
    private Long liveStreamId;
    @NotEmpty(message = "content can't empty")
    private String content;

    private Long userId;
    private Long liveMessageParentId;
}
