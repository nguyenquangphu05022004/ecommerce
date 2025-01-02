package com.example.ecommerce.system.controller.app.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.Data;

@Data
public class UserMemberResVO {
    private String avatar;
    private String fullName;
    private String email;
    private String phoneNumber;
    private UserMember.Sex sex;
    public UserMemberResVO(UserMember userMember) {
        this.avatar = userMember.getAvatar();
        this.fullName = userMember.getFullName();
        this.email = userMember.getEmail();
        this.phoneNumber = userMember.getPhoneNumber();
        this.sex = userMember.getSex();
    }
}
