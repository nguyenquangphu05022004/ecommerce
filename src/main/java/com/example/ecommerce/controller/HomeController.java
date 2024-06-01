package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.CategoryDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.TrackProductSellerDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.service.*;
import com.example.ecommerce.utils.SystemUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Controller
public class HomeController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final ITrackProductSellerService trackProductSellerService;
    private final IUserService userService;
    private final IBasketService basketService;

    @Autowired
    public HomeController(@Qualifier("productService") IProductService productService,
                          @Qualifier("categoryService") ICategoryService categoryService,
                          @Qualifier("trackProductService") ITrackProductSellerService trackProductSellerService,
                          @Qualifier("userService") IUserService userService, IBasketService basketService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.trackProductSellerService = trackProductSellerService;
        this.userService = userService;
        this.basketService = basketService;
    }

    @GetMapping("/admin/home")
    public String getHomeAdmin() {
        return "admin/admin-control";
    }
    /**
     * Chức năng:
     * 1, Xem danh mục, sản phẩm
     * 2, Tạo đơn hàng, giỏ hàng
     */
    @RequestMapping({"/", "/home"})
    public String getHomePage(Model model, HttpServletResponse response) {
        List<ProductDto> productDtos = productService.findAll(0);
        List<CategoryDto> categoryDtos = categoryService.records();
        List<TrackProductSellerDto> trackProductSellerDtos =
                trackProductSellerService.getListTopNumberByNumberOfSold(1, 9);
        Cookie numberOfBasketCookie = new Cookie("basket", basketService.count().toString());
        numberOfBasketCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        response.addCookie(numberOfBasketCookie);
        model.addAttribute("trackProductSellers", trackProductSellerDtos);
        model.addAttribute("products", productDtos);
        model.addAttribute("categories", categoryDtos);
        return "index";
    }

    @GetMapping("/shop")
    public String getShopPage(Model model,
                              @RequestParam(name = "page", defaultValue = "1", required = false) int page) {
        List<CategoryDto> categoryDtos = categoryService.records();
        List<ProductDto> productDtos = productService.findAll(page - 1);
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
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
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
        UserDto user = userService.findUserByUsername(SecurityUtils.username());
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

}
