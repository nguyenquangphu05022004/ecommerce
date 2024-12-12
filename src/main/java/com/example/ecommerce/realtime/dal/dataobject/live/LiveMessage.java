package com.example.ecommerce.realtime.dal.dataobject.live;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;

import java.util.Set;

public class LiveMessage {
    private String content;
    private Integer like;
    private UserMember userMember;

    private LiveStream liveStream;


    private LiveMessage parentMessage;

    private Set<LiveMessage> childrenMessage;
}
