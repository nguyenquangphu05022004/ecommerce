package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.model.binding.OrderRequest;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.response.OperationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAllOrderCreatedByCustomer(
            @RequestParam("orderStatus") OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.getAllOrderByCustomer(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.ok(String.format("Order id %s was deleted by you", orderId));
    }
}
