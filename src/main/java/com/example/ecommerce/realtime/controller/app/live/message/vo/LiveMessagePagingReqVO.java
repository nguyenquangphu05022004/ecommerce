package com.example.ecommerce.realtime.controller.app.live.message.vo;

import lombok.Data;

@Data
public class LiveMessagePagingReqVO {
    private Long liveStreamId;
    private Integer currentPage;
}
