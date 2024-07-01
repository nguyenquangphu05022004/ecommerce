package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Status;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.domain.dto.*;
import com.example.ecommerce.repository.NotificationRepository;
import com.example.ecommerce.repository.StockClassificationRepository;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PageController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final IUserService userService;
    private final IStockService stockService;
    private final StockClassificationRepository classificationRepository;
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
        setNotifications(model);
        return "index";
    }

    @GetMapping("/user/my-basket")
    public String getPageBasket(Model model) {
        List<BasketDto> baskets = basketService.records();
        model.addAttribute("baskets", baskets);
        setNotifications(model);
        return "basket";
    }

    @GetMapping("/shop")
    public String getShopPage(
            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
            @RequestParam(value = "vendorId", defaultValue = "0") Long vendorId,
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "startPrice", defaultValue = "0") Integer startPrice,
            @RequestParam(value = "endPrice", defaultValue = "0") Integer endPrice,
            @RequestParam(value="page", defaultValue = "1") int page,
            @RequestParam(value = "sortProductType", defaultValue = "DEFAULT") SortProductType sortProductType,
            Model model) {
        List<ProductDto> products = productService.searchProduct(
                categoryId,
                vendorId,
                query,
                startPrice,
                endPrice,
                sortProductType,
                page - 1
        );
        model.addAttribute("sortTypes", Arrays.asList(SortProductType.values()));
        model.addAttribute("vendorId", vendorId);
        model.addAttribute("products", products);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("keyword", query);
        model.addAttribute("totalPage", SystemUtils.totalPage);
        model.addAttribute("startPrice", startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("sortProductType", sortProductType);
        model.addAttribute("page", page - 1);
        setNotifications(model);
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

    @GetMapping("/orders/product/**")
    public String pageOrder(@RequestParam("numberOfProduct") Integer numberOfProduct,
                            @RequestParam("stockId") Long stockId,
                            @RequestParam("stockType") Long stockClassificationId,
                            Model model) {
        StockResponse stockDto = stockService.findById(stockId);
        UserResponseInfo userResponseInfo = userService
                .findUserByUsername(SecurityUtils.username());
        Optional<StockClassification> exist =
                classificationRepository.findById(stockClassificationId);
        if (exist.isPresent()) model.addAttribute("stockType", exist.get());
        model.addAttribute("stock", stockDto);
        model.addAttribute("user", userResponseInfo);
        model.addAttribute("totalPrice",
                SystemUtils.getFormatNumber((
                        numberOfProduct * stockDto.getPrice()
                                + stockDto.getProduct()
                                .getVendor()
                                .getPerMoneyDelivery())));
        model.addAttribute("numberOfProduct", numberOfProduct);
        setNotifications(model);
        return "order";
    }

    @GetMapping("/vendor/orders")
    public String getPageOrderAdmin() {
        return "admin/order-product";
    }

    @GetMapping("/user/order")
    public String getOrderPageOfUser(Model model) {
        model.addAttribute("statusOrders", Arrays.asList(Status.values()));
        setNotifications(model);
        return "orders-user";
    }

    @GetMapping("/products/{productId}/**")
    public String getPageDetailProduct(@PathVariable("productId") Long productId,
                                       Model model) {
        ProductDto productDto = productService.findById(productId);
        boolean wasBoughtByUser = productService.productWasBoughtByUser(productId, SecurityUtils.username());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("evaluation", new EvaluationRequest());
        model.addAttribute("was_bought", wasBoughtByUser);
        model.addAttribute("product", productDto);
        model.addAttribute("products", productService.findProductByCategoryId(productDto.getCategory().getId(), 0));
        setNotifications(model);
        return "product";
    }

    private void setNotifications(Model model) {
        if (SecurityUtils.username() != null) {
            List<NotificationResponse> notifications = userService
                    .findUserByUsername(SecurityUtils.username())
                    .getNotifications();
            model.addAttribute("numberOfNotification", notifications.size());
            model.addAttribute("notifications", notifications);
        }
    }

}
