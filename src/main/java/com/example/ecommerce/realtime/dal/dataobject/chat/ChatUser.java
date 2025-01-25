package com.example.ecommerce.realtime.dal.dataobject.chat;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "realtime_chat")
@Setter
@Getter
@NoArgsConstructor
public class ChatUser extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_one_id")
    private UserMember userOne;
    @ManyToOne
    @JoinColumn(name = "user_two_id")
    private UserMember userTwo;


    @Transient
    public UserMember getUserChat() {
        Long userLoginId = SecurityUtils.getLoginUserMemberId();
        assert userLoginId != null;
        if(userLoginId.equals(userOne.getId())) {
            return userTwo;
        }
        return userOne;
    }

}
