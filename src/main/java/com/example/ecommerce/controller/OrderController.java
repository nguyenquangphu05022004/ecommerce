package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.model.binding.order.OrderRequest;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.response.OperationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")

public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody @Valid OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return ResponseEntity.ok(new OperationResponse(true, "cretead order", 200));
    }

    @GetMapping("/customer")
    public List<OrderViewModel> getAllOrderCreatedByCustomer(
            @RequestParam(value = "orderStatus", required = false) OrderStatus status
    ) {
        List<OrderViewModel> orders = orderService.getAllOrderByCustomer(status);
        return orders;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.ok(new OperationResponse(
                true,
                "delete order success",
                200
        ));
    }
}
