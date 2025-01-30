package com.example.ecommerce.realtime.controller.app.live.comment.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LiveCommentCreateReqVO {
    @NotNull(message = "live stream can't null")
    private Long livestreamId;
    @NotEmpty(message = "content can't empty")
    private String content;
    private Boolean pin;
    private Long userId = SecurityUtils.getLoginUserMemberId();
}
