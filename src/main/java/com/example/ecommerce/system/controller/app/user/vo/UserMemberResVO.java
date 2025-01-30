package com.example.ecommerce.system.controller.app.user.vo;

import com.example.ecommerce.frame.common.validate.user.UserUtils;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.Data;

@Data
public class UserMemberResVO {
    private String avatar;
    private String fullName;
    private String aliasName;
    private String email;
    private String phoneNumber;
    private UserMember.Sex sex;
    private Boolean isSeller;
    public UserMemberResVO(UserMember userMember) {
        this.avatar = userMember.getAvatar();
        this.fullName = userMember.getFullName();
        this.email = userMember.getEmail();
        this.phoneNumber = userMember.getPhoneNumber();
        this.sex = userMember.getSex();
        this.isSeller = (userMember instanceof Seller);
        this.aliasName = isSeller ? ((Seller)userMember).getShopName() : "";
    }
}
