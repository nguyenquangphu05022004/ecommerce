package com.example.ecommerce.promotion.controller.app.coupon.vo;

import com.example.ecommerce.frame.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AppPageCouponReqVO extends PageParam {
    @Schema(description = "User nguoi tao ra phieu giam gia", example = "1")
    private Long userId;
}
