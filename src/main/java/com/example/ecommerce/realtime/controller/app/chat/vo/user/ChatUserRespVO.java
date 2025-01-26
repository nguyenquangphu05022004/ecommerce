package com.example.ecommerce.realtime.controller.app.chat.vo.user;

import lombok.Data;

@Data
public class ChatUserRespVO {
    private Long id;
    private String userFullName;
    private Long userId;
    private String userAvatar;
    private Boolean online;
//    private MessageSimpleRespVO message;
    private Long numberUnreadMessage;

}
