package com.example.ecommerce.realtime.controller.app.chat.vo;

import com.example.ecommerce.realtime.dal.dataobject.chat.Message;
import lombok.Getter;

import java.util.List;

@Getter
public class MessageRespVO extends MessageSimpleRespVO{
    private List<String> mediaUrls;
    private String position;
    public MessageRespVO(Message msg) {
        super(msg);
        this.mediaUrls = msg.getMediaUrls();
        this.position = msg.getPosition().name();
    }
}
