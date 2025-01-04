package com.example.ecommerce.trade.dal.repo.order;

import com.example.ecommerce.trade.dal.dataobject.order.Order;
import com.example.ecommerce.trade.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
//    @Override
    List<Order> findAllByUserMemberId(Long userMemberId);
    Optional<Order> findByUserMemberIdAndId(Long userMemberId, Long orderId);
    List<Order> findAllByUserMemberIdAndOrderStatus(Long userMemberId, OrderStatus orderStatus);

}
