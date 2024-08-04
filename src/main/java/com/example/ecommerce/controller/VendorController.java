package com.example.ecommerce.controller;


import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.service.request.CouponRequest;
import com.example.ecommerce.service.request.VendorRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/vendors")
@CrossOrigin("*")

public class VendorController {
    private final IVendorService vendorService;

    @PostMapping
    public ResponseEntity<?> createVendor(@RequestBody VendorRequest request) {
        vendorService.saveOrUpdate(request);
        return ResponseEntity.ok(
                new OperationResponse(
                        true,
                        "you created successfull",
                        HttpStatus.OK.value())
        );
    }

    @PutMapping("/follow/{vendorId}")
    public ResponseEntity<?> userFollowVendor(
            @PathVariable("vendorId") Long vendorId
    ) {
        vendorService.userFollow(vendorId);
        return ResponseEntity.ok(
                new OperationResponse(
                        true,
                        "you created successfull",
                        HttpStatus.OK.value())
        );
    }
    @DeleteMapping("/follow")
    public ResponseEntity<?> cancelFollowVendor(
            @RequestParam("userId") Long userId,
            @RequestParam("vendorId") Long vendorId
    ) {
        vendorService.cancelFollowVendor(userId, vendorId);
        return ResponseEntity.ok(
                new OperationResponse(
                        true,
                        "you created successfull",
                        HttpStatus.OK.value())
        );
    }

    @PostMapping("/coupons")
    public ResponseEntity<?> createCoupon(@RequestBody CouponRequest couponRequest) {
        vendorService.createCoupon(couponRequest);
        return ResponseEntity.ok(
                new OperationResponse(
                        true,
                        "you created successfull",
                        HttpStatus.OK.value())
        );
    }

    @GetMapping("/coupons/{vendorId}")
    public ResponseEntity<?> getCouponByVendorIdAndCouponCode(
            @PathVariable("vendorId") Long vendorId,
            @RequestParam("couponCode") String code
    ) {
        return ResponseEntity.ok(vendorService.checkCouponExpire(vendorId, code));
    }

}
