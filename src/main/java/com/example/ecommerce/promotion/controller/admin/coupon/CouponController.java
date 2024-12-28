package com.example.ecommerce.promotion.controller.admin.coupon;

import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.promotion.controller.admin.coupon.vo.CouponCreateReqVO;
import com.example.ecommerce.promotion.controller.admin.coupon.vo.CouponRespVO;
import com.example.ecommerce.promotion.service.coupon.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin-api/promotion/coupons")
@Tag(name = "Admin_Coupon")
public class CouponController {
    private final CouponService couponService;

    @PostMapping
    @PreAuthorize("@ss.hasPermission('promotion-coupon:update')")
    @Operation(summary = "Tao phieu giam gia")
    public CommonResult<CouponRespVO> createCoupon(@RequestBody CouponCreateReqVO req) {
        return CommonResult.success(couponService.createCoupon(req), CouponRespVO::new);
    }


    @GetMapping("/owner/page")
    @Operation(summary = "Lay danh sach coupon cua admin(or seller)")
    public CommonResult<PageResult<CouponRespVO>> getPageCouponByOwner(@RequestBody PageParam req) {
        return CommonResult.success(couponService.getPageCoupon(SecurityUtils.getLoginUserMemberId(), req), CouponRespVO::new);
    }

    @PutMapping("/revoke/{id}")
    @Operation(summary = "Thu hoi phieu giam gia")
    public CommonResult<Boolean> revokeCoupon(@PathVariable("id") Long id) {
        couponService.revokeCoupon(id);
        return CommonResult.success(true);
    }

}
