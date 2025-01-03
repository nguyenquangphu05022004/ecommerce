package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.trade.dal.dataobject.order.OrderLog;
import com.example.ecommerce.trade.enums.OrderStatus;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface OrderLogService {

    List<OrderLog> getListByOrderId(Long orderId);
    OrderLog getLatestLogByOrderId(Long orderId);
    void deleteOrderLog(Long id);

    @Async
    void createOrderLog(Long orderId, String content, OrderStatus prev, OrderStatus next);

}
