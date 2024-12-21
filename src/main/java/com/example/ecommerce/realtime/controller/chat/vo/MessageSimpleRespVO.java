package com.example.ecommerce.realtime.controller.chat.vo;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.common.date.DateUtils;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.realtime.dal.dataobject.chat.Message;
import lombok.Data;

@Data
public class MessageSimpleRespVO {
    private Long id;
    private String createdTime;
    private String content;
    private String sender;
    private Boolean revokeMessage;
    public MessageSimpleRespVO(Message msg) {
        this.id = msg.getId();
        this.createdTime = DateTimeUtils.format(msg.getCreatedDate());
        this.content = StringUtils.cut(msg.getContent(), 10);
        this.sender = msg.getSender();
        this.revokeMessage = msg.getRevokeMessage();
    }


}