package com.example.ecommerce.realtime.controller.app.live.message.vo;

import com.example.ecommerce.system.controller.app.user.vo.UserMemberResVO;
import lombok.Data;

@Data
public class LiveMessageRespVO {
    private UserMemberResVO user;
    private String content;
    private Integer like;

    private LiveMessageRespVO parentMessage;

}
