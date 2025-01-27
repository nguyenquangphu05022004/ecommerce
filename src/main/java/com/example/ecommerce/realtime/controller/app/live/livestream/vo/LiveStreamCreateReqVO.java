package com.example.ecommerce.realtime.controller.app.live.livestream.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LiveStreamCreateReqVO {
    private String title;
    private Long userId = SecurityUtils.getLoginUserMemberId();
    private LocalDateTime startDate;
}
