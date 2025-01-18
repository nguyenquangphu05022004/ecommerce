package com.example.ecommerce.system.controller.app.user.vo;

import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import lombok.Data;

@Data
public class AddressCreateReqVO {

    private String city;
    private String district;
    private String commune;

    private String detailAddress;

    private String fullName;
    private String phoneNumber;
    private Boolean defaultAddress;
    private Long userMemberId = SecurityUtils.getLoginUserMemberId();

}
