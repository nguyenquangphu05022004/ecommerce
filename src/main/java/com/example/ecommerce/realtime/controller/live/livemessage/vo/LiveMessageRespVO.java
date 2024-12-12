package com.example.ecommerce.realtime.controller.live.livemessage.vo;

import com.example.ecommerce.system.controller.user.vo.UserMemberResVO;
import lombok.Data;

@Data
public class LiveMessageRespVO {
    private UserMemberResVO user;
    private String content;
    private Integer like;

    private LiveMessageRespVO parentMessage;

}
