package com.example.ecommerce.system.controller.admin.user.vo;

import lombok.Data;

@Data
public class SellerCreateReqVO extends UserMemberCreateReqVO{
    private String shopName;
    private String shopImageUrl;
}
