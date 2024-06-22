package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.dto.SortProductType;
import com.example.ecommerce.domain.dto.CategoryDto;
import com.example.ecommerce.domain.dto.ProductDto;
import com.example.ecommerce.domain.dto.UserRequest;
import com.example.ecommerce.domain.dto.UserResponseInfo;
import com.example.ecommerce.domain.dto.UserTrack;
import com.example.ecommerce.service.*;
import com.example.ecommerce.utils.SortUtils;
import com.example.ecommerce.utils.SystemUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final IUserService userService;
    private final IBasketService basketService;


    @GetMapping("/admin/home")
    public String getHomeAdmin() {
        return "admin/admin-control";
    }

    @RequestMapping({"/", "/home", ""})
    public String getHomePage(Model model, HttpServletResponse response) {
        List<ProductDto> productDtos = productService.findAll(0, SystemUtils.NUMBER_OF_ITEM - 1);
        List<CategoryDto> categoryDtos = categoryService.getAll();
        List<ProductDto> productTopSellers = productService.getAll();
        SortUtils.sortProduct(
                SortProductType.NUMBER_OF_SELLER,
                productTopSellers
        );

        Cookie numberOfBasketCookie = new Cookie("basket", basketService.count().toString());
        numberOfBasketCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        response.addCookie(numberOfBasketCookie);
        model.addAttribute(
                "productTopSellers",
                productTopSellers
                        .stream()
                        .limit(SystemUtils.NUMBER_OF_ITEM)
                        .toList());
        model.addAttribute("products", productDtos);
        model.addAttribute("categories", categoryDtos);
        return "index";
    }

    @GetMapping("/shop")
    public String getShopPage(Model model,
                              @RequestParam(name = "page", defaultValue = "1", required = false) int page) {
        List<CategoryDto> categoryDtos = categoryService.getAll();
        List<ProductDto> productDtos = productService.findAll(page - 1, SystemUtils.NUMBER_OF_ITEM);
        model.addAttribute("sortTypes", Arrays.asList(SortProductType.values()));
        model.addAttribute("sortProductType", SortProductType.DEFAULT);
        model.addAttribute("categories", categoryDtos);
        model.addAttribute("products", productDtos);
        model.addAttribute("totalPage", SystemUtils.totalPage);
        model.addAttribute("page", page - 1);
        return "shop";
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String getRegisterPage(Model model) {
        UserRequest request = new UserRequest();
        model.addAttribute("user", request);
        return "register";
    }

    @GetMapping("/seller")
    public String getPageVendorSeller() {
//        return "admin/user/vendor-seller";
        return "contact";
    }

    @GetMapping("/404")
    public String getPage404() {
        return "404";
    }

    @GetMapping("/user/profile")
    public String getPageUserProfile(Model model) {
        UserResponseInfo user = userService.findUserByUsername(SecurityUtils.username());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/change-password")
    public String getPageChangePassword() {
        return "change-password";
    }

    @GetMapping("/forget-password")
    public String getPageForgetPassword() {
        return "forget-password";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    @GetMapping("/system/logout")
    public String logout(HttpServletRequest request) {
        log.info("User {} was logout", SecurityUtils.username());
        UserTrack.getInstance().getMap().remove(SecurityUtils.username());
        request.getSession().invalidate();
        return "redirect:/login?logout";
    }

}
