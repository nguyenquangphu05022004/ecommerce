package com.example.ecommerce.realtime.controller.admin.chat.vo;

import lombok.Data;

import java.util.List;

@Data
public class MessageTemplateRespVO extends MessageTemplateSimpleRespVO{
    private String name;
    private List<String> params;
    private String content;
}
