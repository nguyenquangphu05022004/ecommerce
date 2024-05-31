package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.OrderDto;
import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.dto.StockResponse;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.service.impl.OrderServiceImpl;
import com.example.ecommerce.service.impl.ProductServiceImpl;
import com.example.ecommerce.service.impl.UserServiceImpl;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final ProductServiceImpl productService;
    private final UserServiceImpl userService;
    private final IStockService stockService;

    @GetMapping("/orders/product/**")
    public String getFormCheckOut(@RequestParam("numberOfProduct") Integer numberOfProduct,
                                  @RequestParam("stockId") Long stockId,
                                  Model model) {
        StockResponse stockDto = stockService.findById(stockId);
        UserDto userDto = userService.findUserByUsername(SecurityUtils.username());
        model.addAttribute("stock", stockDto);
        model.addAttribute("user", userDto);
        model.addAttribute("totalPrice",
                SystemUtils.getFormatNumber((
                        numberOfProduct * stockDto.getPrice()
                        + stockDto.getProductResponse()
                        .getVendorResponse()
                        .getPerMoneyDelivery())));
        model.addAttribute("numberOfProduct", numberOfProduct);
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
    public OrderDto createOrder(@RequestBody OrderRequest orderDto) {
        return orderService.saveOrUpdate(orderDto);
    }
}
