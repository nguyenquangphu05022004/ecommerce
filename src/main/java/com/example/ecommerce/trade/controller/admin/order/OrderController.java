package com.example.ecommerce.trade.controller.admin.order;

import com.example.ecommerce.trade.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin-api/trade/orders")
public class OrderController {
    private final OrderService orderService;

}
