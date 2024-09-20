package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.FilterOrderRequest;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "${api.version}"+ "/orders")
@CrossOrigin("*")

public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    public APIResponse<?> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
       return orderService.createOrder(orderRequest);
    }


    @PutMapping("/{id}")
    public APIResponse<?> updateOrderState(@PathVariable("id") Long orderId,
                                           @RequestParam("isNext") boolean isNext) {
        return this.orderService.updateOrderState(orderId, isNext);
    }

    @GetMapping
    public APIListResponse<?> getAllOrderCreatedByCustomer(
            @RequestBody FilterOrderRequest filterOrderRequest
    ) {
        return orderService.getAllOrderByCustomer(filterOrderRequest);
    }

    @DeleteMapping("/{id}")
    public APIResponse<?> deleteOrderById(@PathVariable("id") Long orderId) {
        return orderService.deleteById(orderId);
    }
}
