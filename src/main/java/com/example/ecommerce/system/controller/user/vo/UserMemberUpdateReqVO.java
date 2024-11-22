package com.example.ecommerce.system.controller.user.vo;

import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.Data;

@Data
public class UserMemberUpdateReqVO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserMember.Sex sex;
}
