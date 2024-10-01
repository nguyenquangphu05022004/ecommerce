package com.example.ecommerce.controller;


import com.example.ecommerce.domain.model.binding.CouponRequest;
import com.example.ecommerce.domain.model.binding.VendorRequest;
import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.domain.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/vendors")
@CrossOrigin("*")
public class VendorController {
    private final IVendorService vendorService;

    @PutMapping("/follow/{vendorId}")
    public APIResponse<?> userFollowVendor(
            @PathVariable("vendorId") Long vendorId
    ) {
        return vendorService.userFollow(vendorId);
    }
    @DeleteMapping("/follow")
    public APIResponse<?> cancelFollowVendor(
            @RequestParam("userId") Long userId,
            @RequestParam("vendorId") Long vendorId
    ) {
        return vendorService.cancelFollowVendor(userId, vendorId);
    }

    @PostMapping("/coupons")
    public APIResponse<?> createCoupon(@RequestBody CouponRequest couponRequest) {
        return vendorService.createCoupon(couponRequest);
    }

    @GetMapping("/coupons/{vendorId}")
    public APIResponse<?> getCouponByVendorIdAndCouponCode(
            @PathVariable("vendorId") Long vendorId,
            @RequestParam("couponCode") String code
    ) {
        return vendorService.checkCouponExpire(vendorId, code);
    }

}
