package com.example.ecommerce.trade.dal.repo.order;

import com.example.ecommerce.trade.dal.dataobject.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
