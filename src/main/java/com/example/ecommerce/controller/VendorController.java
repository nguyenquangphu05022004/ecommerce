package com.example.ecommerce.controller;


import com.example.ecommerce.dto.VendorDto;
import com.example.ecommerce.service.impl.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VendorController {
    /**
     * Chức năng của người bán:
     *  1, Tạo, xóa các sản phẩm
     *  2, Cập nhật trạng thái các đơn hàng
     *  3,
     */
    private final VendorServiceImpl vendorService;

    @Autowired
    public VendorController(VendorServiceImpl vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/admin/users/{userId}/vendor")
    public String getFormUpdateUserToVendor(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "admin/user/update-users";
    }
    @PostMapping("/vendors")
    public VendorDto updateUserToVendor(@RequestBody VendorDto vendorDto) {
        VendorDto vendoer =  vendorService.saveOrUpdate(vendorDto);
        return vendoer;
    }
}
