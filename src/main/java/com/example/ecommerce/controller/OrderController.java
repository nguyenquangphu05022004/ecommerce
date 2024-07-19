package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.OrderStatus;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.dto.OrderDto;
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
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderRequest) {
        orderService.createOrder(orderRequest);
        return ResponseEntity.ok(String.format("%s you created order", SecurityUtils.username()));
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getAllOrderCreatedByCustomer(
            @RequestParam("orderStatus") OrderStatus status
    ) {
        List<OrderDto> orders = orderService.getAllOrderByCustomer(status);
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable("id") Long orderId) {
        orderService.deleteById(orderId);
        return ResponseEntity.ok(String.format("Order id %s was deleted by you", orderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderApproval(
            @PathVariable("id") Long id,
            @RequestParam("approval") Boolean approval
    ) {
        orderService.approval(id, approval);
        return ResponseEntity.ok(String.format("Order with id was updated by you has a status approval = %s", id, approval));
    }
}
