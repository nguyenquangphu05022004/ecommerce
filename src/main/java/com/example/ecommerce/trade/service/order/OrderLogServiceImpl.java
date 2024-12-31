package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.trade.dal.dataobject.order.OrderLog;
import com.example.ecommerce.trade.dal.repo.order.OrderLogRepository;
import com.example.ecommerce.trade.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLogServiceImpl implements OrderLogService{
    private final OrderLogRepository orderLogRepository;
    @Override
    public List<OrderLog> getListByOrderId(Long orderId) {
        return List.of();
    }

    @Override
    public OrderLog getLatestLogByOrderId(Long orderId) {
        return null;
    }

    @Override
    public void deleteOrderLog(Long id) {

    }

    @Override
    public OrderLog createOrderLog(Long orderId, String content, OrderStatus prev, OrderStatus next) {
        return null;
    }
}
