package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.trade.controller.admin.order.self.vo.PageOrderReqVO;
import com.example.ecommerce.trade.controller.app.order.vo.OrderDetailsReqVO;
import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderDetailsReqVO reqVO);
    void cancelOrder(Long userId, Long orderId);
    List<Order> getAllListOrder(Long userId);
    List<Order> getAllListOrderByStatus(Long userId, OrderStatus orderStatus);

    Order getOrderById(Long orderId);
    Order getOrderByUserIdAndOrderId(Long userId, Long orderId);

    boolean userHasOrderProduct(Long userId, Long spuId);


    PageResult<Order> getPageOrder(PageOrderReqVO req);

    void updateNextStatus(Long orderId, String content);
    void updatePreviousStatus(Long orderId, String content);
    void approvalOrder(Long orderId);

}
