package com.example.ecommerce.system.controller.admin.user.vo;

import lombok.Data;

@Data
public class UserMemberUpdatePasswordReqVO {
    private String oldPassword;
    private String newPassword;
}
