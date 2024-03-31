package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.entity.UserContactDetails;
import com.example.ecommerce.service.impl.OrderServiceImpl;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import com.example.ecommerce.service.impl.UserServiceImpl;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    private final OrderServiceImpl orderService;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;

    @Autowired
    public OrderController(OrderServiceImpl orderService,
                           ProductServiceImpl productService,
                           UserServiceImpl userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/orders/product/{productId}/**")
    public String getFormCheckOut(@PathVariable("productId") Long productId,
                                  @RequestParam("numberOfProduct") Integer numberOfProduct,
                                  Model model) {
        ProductDto productDto = productService.findById(productId);
        UserDto userDto = userService.findUserByUsername(SecurityUtils.username());
        model.addAttribute("user", userDto);
        model.addAttribute("product", productDto);
        model.addAttribute("totalPrice",
                (SystemUtils.getFormatNumber(numberOfProduct
                        * productDto.getPrice()
                        + productDto.getVendor().getPerMoneyDelivery())));
        return "order";
    }

    @GetMapping("/orders")
    @ResponseBody
    public List<OrderDto> getListOrderOfUser() {
        return orderService.records();
    }

    @GetMapping("/orders/{status}")
    @ResponseBody
    public List<OrderDto> getListOrderOfUser(@PathVariable("status") Status status) {
        return orderService.records(status);
    }

    @PostMapping("/orders")
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.saveOrUpdate(orderDto);
    }
}
