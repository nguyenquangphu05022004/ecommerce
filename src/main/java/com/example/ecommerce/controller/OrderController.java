package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.dto.ENUM.SelectFilterOrder;
import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.domain.dto.product.OrderDto;
import com.example.ecommerce.domain.dto.product.OrderRequest;
import com.example.ecommerce.domain.dto.product.StockResponse;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.impl.OrderServiceImpl;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderService;
    private final IUserService userService;
    private final IStockService stockService;

    @GetMapping("/orders/product/**")
    public String getFormCheckOut(@RequestParam("numberOfProduct") Integer numberOfProduct,
                                  @RequestParam("stockId") Long stockId,
                                  Model model) {
        StockResponse stockDto = stockService.findById(stockId);
        UserResponseInfo userResponseInfo = userService.findUserByUsername(SecurityUtils.username());
        model.addAttribute("stock", stockDto);
        model.addAttribute("user", userResponseInfo);
        model.addAttribute("totalPrice",
                SystemUtils.getFormatNumber((
                        numberOfProduct * stockDto.getPrice()
                        + stockDto.getProduct()
                        .getVendor()
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

    @DeleteMapping("/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
    }

    @PutMapping("/orders/{orderId}/approval")
    @ResponseStatus(HttpStatus.OK)
    public void approvalOrder(@PathVariable("orderId") Long orderId) {
        orderService.approval(orderId);
    }

    @PostMapping("/orders")
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderRequest orderDto) {
        return orderService.saveOrUpdate(orderDto);
    }

    @GetMapping("/user/order")
    public String getOrderPageOfUser() {
        return "orders-user";
    }
    @GetMapping("/vendor/orders")
    public String getOrderPageVendor() {
        return "admin/order-product";
    }
    @GetMapping("/vendor/orders/product")
    @ResponseBody
    public List<OrderDto> getListOrder() {
        return orderService.getAllOrder();
    }
    @GetMapping("/vendor/orders/product/{selectFilterOrder}")
    @ResponseBody
    public List<OrderDto> getListOrder(@PathVariable("selectFilterOrder") SelectFilterOrder selectFilerOrder) {
        return orderService.getAllOrder(selectFilerOrder);
    }
}
