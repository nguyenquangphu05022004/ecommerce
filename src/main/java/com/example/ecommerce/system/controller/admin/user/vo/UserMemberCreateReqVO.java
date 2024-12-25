package com.example.ecommerce.system.controller.admin.user.vo;


import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.Data;

@Data
public class UserMemberCreateReqVO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private UserMember.Sex sex;
}
