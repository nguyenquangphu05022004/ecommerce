package com.example.ecommerce.realtime.controller.app.chat.vo;

import lombok.Data;

@Data
public class ConversationRespVO extends ConversationSimpleRespVO{
    private MessageSimpleRespVO message;
    private Integer numberUnreadMessage;
}
