package com.example.ecommerce.realtime.controller.app.live.comment.vo;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.realtime.dal.dataobject.live.LiveComment;
import com.example.ecommerce.system.controller.app.user.vo.UserMemberResVO;
import lombok.Data;

@Data
public class LiveCommentRespVO {
    private Long id;
    private UserMemberResVO user;
    private String content;
    private Integer like;
    private String createdDate;
    private Boolean isPinned;
    private Boolean isDeleted;

    public LiveCommentRespVO(LiveComment lc) {
        this.user = new UserMemberResVO(lc.getUserMember());
        this.content = lc.getContent();
        this.like = lc.getLikeComment();
        this.isPinned = lc.getIsPinned();
        this.id = lc.getId();
        this.createdDate = DateTimeUtils.format(lc.getCreatedDate());
        this.isDeleted = false;
    }
}
