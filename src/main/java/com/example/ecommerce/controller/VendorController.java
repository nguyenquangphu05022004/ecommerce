package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CouponDto;
import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.service.impl.CouponServiceImpl;
import com.example.ecommerce.service.impl.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VendorController {
    /**
     * Chức năng của người bán:
     *  1, Tạo, xóa các sản phẩm
     *  2, Cập nhật trạng thái các đơn hàng
     *  3,
     */
    private final VendorServiceImpl vendorService;
    private final CouponServiceImpl couponService;

    @Autowired
    public VendorController(VendorServiceImpl vendorService, CouponServiceImpl couponService) {
        this.vendorService = vendorService;
        this.couponService = couponService;
    }

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
        List<VendorDto> vendorDtos = vendorService.records();
        model.addAttribute("vendors", vendorDtos);
        model.addAttribute("coupons", list);
        model.addAttribute("vendorId", vendorId);
        return "coupon";
    }
    @PostMapping("/vendor/product/{productId}/coupon")
    @ResponseBody
    public CouponDto checkCouponExistsOrValid(@PathVariable("productId") Long productId,
                                                  @RequestParam("couponCode") String couponCode) {
        return couponService.findByCodeAndProductId(couponCode, productId);
    }
}
