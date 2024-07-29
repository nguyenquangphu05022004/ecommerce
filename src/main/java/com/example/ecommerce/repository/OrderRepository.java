package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCreatedByAndOrderStatus(String createdBy, OrderStatus orderStatus);

    @Modifying
    @Query("update Order o set o.approval = :approval where o.id = :id")
    void updateApprovalById(@Param("id") Long id,
                            @Param("approval") Boolean approval);

    List<Order> findAllByCreatedBy(String username);
}
