package com.example.ecommerce.system.controller.user.vo;

import lombok.Data;

@Data
public class SellerCreateReqVO extends UserMemberCreateReqVO{
    private String shopName;
    private String shopImageUrl;
}
