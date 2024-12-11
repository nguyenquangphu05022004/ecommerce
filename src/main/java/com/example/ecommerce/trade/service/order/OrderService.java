package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutReqVO;
import com.example.ecommerce.trade.controller.order.vo.OrderCheckoutResVO;
import com.example.ecommerce.trade.controller.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderStatus;

import java.util.List;

public interface OrderService {
    void createOrder(Long userId, OrderDetailsReqVO reqVO);
    void cancelOrder(Long orderId);
    OrderCheckoutResVO checkout(Long userId, OrderCheckoutReqVO reqVO);

    List<Order> getAllListOrder(Long userId);
    List<Order> getAllListOrderByStatus(Long userId, OrderStatus orderStatus);

    void updateNextStatus(Long orderId);
    void updatePreviousStatus(Long orderId);
}
