package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCreatedByAndOrderStatus(String createdBy, OrderStatus orderStatus);
    List<Order> findAllByStockProductVendorUserUsername(String username);
    List<Order> findAllByStockProductVendorUserUsernameAndApproval(String username, boolean approval);
    List<Order> findAllByStockProductVendorUserUsernameAndPurchased(String username, boolean purchased);

    @Modifying
    @Query("update Order o set o.approval = :approval where o.id = :id")
    void updateApprovalById(@Param("id") Long id,
                            @Param("approval") Boolean approval);
}
