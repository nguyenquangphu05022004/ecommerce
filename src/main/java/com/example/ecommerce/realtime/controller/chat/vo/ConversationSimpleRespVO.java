package com.example.ecommerce.realtime.controller.chat.vo;

import lombok.Data;

@Data
public class ConversationSimpleRespVO {
    private String displayName;
    private Long userId;
    private String thumbnailAvatar;
    private Boolean chat;
}
