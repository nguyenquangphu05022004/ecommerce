package com.example.ecommerce.trade.service.order;

import com.example.ecommerce.trade.dal.dataobject.order.Order;
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
        return this.orderLogRepository.findAll();
    }

    @Override
    public OrderLog getLatestLogByOrderId(Long orderId) {
        List<OrderLog> orderLogs = getListByOrderId(orderId);
        return orderLogs.get(orderLogs.size() - 1);
    }

    @Override
    public void deleteOrderLog(Long id) {
        this.orderLogRepository.deleteById(id);
    }

    @Override
    public void createOrderLog(Long orderId, String content, OrderStatus prev, OrderStatus next) {
        OrderLog orderLog = OrderLog.builder().order(Order.builder().id(orderId).build())
                .content(content).previousStatus(prev).nextStatus(next).build();
        this.orderLogRepository.save(orderLog);
    }
}
