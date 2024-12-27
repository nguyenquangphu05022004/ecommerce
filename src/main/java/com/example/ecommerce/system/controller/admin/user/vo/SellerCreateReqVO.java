package com.example.ecommerce.system.controller.admin.user.vo;

import com.example.ecommerce.system.controller.app.user.vo.UserMemberCreateReqVO;
import lombok.Data;

@Data
public class SellerCreateReqVO extends UserMemberCreateReqVO {
    private String shopName;
    private String shopImageUrl;
}
