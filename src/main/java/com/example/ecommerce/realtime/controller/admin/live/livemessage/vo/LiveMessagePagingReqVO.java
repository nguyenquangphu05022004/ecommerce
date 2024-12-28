package com.example.ecommerce.realtime.controller.admin.live.livemessage.vo;

import lombok.Data;

@Data
public class LiveMessagePagingReqVO {
    private Long liveStreamId;
    private Integer currentPage;
}
