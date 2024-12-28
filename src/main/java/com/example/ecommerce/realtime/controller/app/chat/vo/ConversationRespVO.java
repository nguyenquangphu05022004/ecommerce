package com.example.ecommerce.realtime.controller.chat.vo;

import lombok.Data;

@Data
public class ConversationRespVO extends ConversationSimpleRespVO{
    private MessageSimpleRespVO message;
    private Integer numberUnreadMessage;
}
