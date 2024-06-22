package com.example.ecommerce.controller;


import com.example.ecommerce.domain.dto.chat.VendorResponseInbox;
import com.example.ecommerce.domain.dto.CouponDto;
import com.example.ecommerce.domain.dto.VendorDto;
import com.example.ecommerce.service.ICouponService;
import com.example.ecommerce.service.IVendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VendorController {
    private final IVendorService vendorService;
    private final ICouponService couponService;

    @GetMapping("/admin/users/{userId}/vendor")
    public String getFormUpdateUserToVendor(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "admin/user/update-users";
    }
    @PostMapping("/vendors")
    public VendorDto updateUserToVendor(@RequestBody VendorDto vendorDto) {
        VendorDto vendor =  vendorService.saveOrUpdate(vendorDto);
        return vendor;
    }
    @GetMapping("/vendor/coupon/add")
    public String getFormCreateCoupon(Model model) {
        CouponDto couponDto = new CouponDto();
        model.addAttribute("coupon", couponDto);
        return "admin/product/create-coupon";
    }
    @PostMapping("/vendor/coupon/add")
    public String createCoupon(@ModelAttribute CouponDto couponDto) {
        couponService.createCoupon(couponDto);
        return "redirect:/vendor/coupons";
    }
    @GetMapping("/vendor/coupons")
    public String getListCouponByVendor(Model model) {
        List<CouponDto> list = couponService.listByVendor();
        model.addAttribute("coupons", list);
        return "admin/product/list-coupon";
    }
    @GetMapping("/coupons")
    public String getListCoupon(Model model, @RequestParam(name = "vendorId", defaultValue = "0", required = false) Long vendorId) {
        List<CouponDto> list = (vendorId == 0) ? couponService.list() : couponService.listByVendor(vendorId);
        List<VendorDto> vendorDtos = vendorService.getAll();
        model.addAttribute("vendors", vendorDtos);
        model.addAttribute("coupons", list);
        model.addAttribute("vendorId", vendorId);
        return "coupon";
    }
    @PostMapping("/coupons/vendor/product/{productId}")
    @ResponseBody
    public CouponDto checkCouponExistsOrValid(@PathVariable("productId") Long productId,
                                              @RequestParam("couponCode") String couponCode) {
        return couponService.findByCodeAndProductId(couponCode, productId);
    }


    @GetMapping("/vendors/name")
    @ResponseBody
    public List<VendorResponseInbox> getListVendorByVendorName(
            @RequestParam("name") String vendorName
    ) {
        return vendorService.findAllByName(vendorName);
    }
}
