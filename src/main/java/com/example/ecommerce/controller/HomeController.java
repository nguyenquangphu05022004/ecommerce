package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.service.impl.CategoryServiceImpl;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ProductServiceImpl productService;
    private final CategoryServiceImpl categoryService;
    @Autowired
    public HomeController(ProductServiceImpl productService,
                              CategoryServiceImpl categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    /**
     * Chức năng:
     *      1, Xem danh mục, sản phẩm
     *      2, Tạo đơn hàng, giỏ hàng
     */
    @RequestMapping({"/", "/home"})
    public String getHomePage(Model model) {
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("categories", categoryDtos);
        return "index";
    }
    @GetMapping("/shop")
    public String getShopPage(Model model) {
        List<CategoryDto> categoryDtos = categoryService.records();
        model.addAttribute("categories", categoryDtos);
        return "shop";
    }
    @GetMapping("/my-order")
    public String getOrderPageOfUser() {
        return "orders-user";
    }
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
    @GetMapping("/sign-up")
    public String getRegisterPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }
}
