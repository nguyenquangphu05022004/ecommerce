package com.example.ecommerce.promotion.controller.app.coupon;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.promotion.controller.admin.coupon.vo.CouponRespVO;
import com.example.ecommerce.promotion.controller.app.coupon.vo.AppPageCouponReqVO;
import com.example.ecommerce.promotion.dal.dataobject.coupon.Coupon;
import com.example.ecommerce.promotion.service.coupon.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "App_Coupon")
@RestController
@RequiredArgsConstructor
@RequestMapping("/app-api/promotion/coupons")
public class AppCouponController {
    private final CouponService couponService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Lay danh sach nguoi tao phieu giam gia, chia trang")
    public CommonResult<PageResult<CouponRespVO>> getPageCouponByUserId(
            @RequestBody AppPageCouponReqVO req
            ) {
        return CommonResult.success(couponService.getPageCoupon(req.getUserId(), req), CouponRespVO::new);
    }

    @GetMapping("/check")
    @Operation(summary = "Kiem tra coupon co hop le")
    public CommonResult<CouponRespVO> checkCouponIsExists(@RequestParam("couponCode") String code) {
        Coupon coupon = this.couponService.getCouponByCode(code);
        return CommonResult.success(coupon, CouponRespVO::new);
    }
}
