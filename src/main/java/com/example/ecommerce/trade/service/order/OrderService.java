package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.trade.controller.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDetailsReqVO reqVO);
    void cancelOrder(Long userId, Long orderId);
    List<Order> getAllListOrder(Long userId);
    List<Order> getAllListOrderByStatus(Long userId, OrderStatus orderStatus);
    void updateNextStatus(Long orderId);
    void updatePreviousStatus(Long orderId);
    Order getOrderById(Long orderId);
    Order getOrderByUserIdAndOrderId(Long userId, Long orderId);
}
