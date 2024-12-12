package com.example.ecommerce.realtime.controller.live.livemessage.vo;

import lombok.Data;

@Data
public class LiveMessagePagingReqVO {
    private Long liveStreamId;
    private Integer currentPage;
}
