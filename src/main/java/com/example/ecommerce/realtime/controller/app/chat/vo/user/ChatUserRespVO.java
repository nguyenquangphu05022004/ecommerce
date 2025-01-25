package com.example.ecommerce.realtime.controller.app.chat.vo.user;

import com.example.ecommerce.realtime.controller.app.chat.vo.MessageSimpleRespVO;
import com.example.ecommerce.realtime.dal.dataobject.chat.ChatUser;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class ChatUserRespVO {
    private Long id;
    private String userFullName;
    private Long userId;
    private String userAvatar;
    private Boolean online;
    private MessageSimpleRespVO message;
    private Integer numberUnreadMessage;

}
