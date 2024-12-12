package com.example.ecommerce.realtime.controller.live.livemessage.vo;

import lombok.Data;

@Data
public class LiveMessageReplyCreateReqVO  extends LiveMessageCreateReqVO{
    private Long liveMessageParentId;
}
