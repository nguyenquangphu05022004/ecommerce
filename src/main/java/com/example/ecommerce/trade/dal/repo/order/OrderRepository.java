package com.example.ecommerce.trade.dal.repo.order;

import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.dal.dataobject.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
//    @Override
    List<Order> findAllByUserMemberId(Long userMemberId);
    List<Order> findAllByUserMemberIdAndOrderStatus(Long userMemberId, OrderStatus orderStatus);

}
