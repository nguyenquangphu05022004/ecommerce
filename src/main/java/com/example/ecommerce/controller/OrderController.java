package com.example.ecommerce.controller;

import com.example.ecommerce.domain.Status;
import com.example.ecommerce.domain.dto.OrderDto;
import com.example.ecommerce.domain.dto.OrderRequest;
import com.example.ecommerce.domain.dto.SelectFilterOrder;
import com.example.ecommerce.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("/orders/{status}")
    @ResponseBody
    public List<OrderDto> getListOrderOfUser(@PathVariable("status") Status status) {
        return orderService.getAllOrderOfCustomer(status);
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

    @GetMapping("/vendor/orders/product")
    @ResponseBody
    public List<OrderDto> getListOrder(@RequestParam("selectFilterOrder")
                                           SelectFilterOrder selectFilerOrder) {
        List<OrderDto> orders = orderService.getAllOrderOfVendor(selectFilerOrder);
        return orders;
    }
}
