package com.example.ecommerce.realtime.dal.dataobject.live;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Getter
@Entity
@Table(name = "realtime_live_comment")
public class LiveComment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_member_id")
    private UserMember userMember;
    @ManyToOne
    @JoinColumn(name = "reply_live_comment")
    private LiveComment replyLiveComment;

    private Integer likeComment;

    private String content;

    @ManyToOne
    @JoinColumn(name = "livestream_id")
    private LiveStream liveStream;


}
