package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.dto.ENUM.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserUsername(@Param("username") String username);
    List<Order> findAllByStockProductVendorUserUsername(String username);
    List<Order> findAllByStockProductVendorUserUsernameAndApproval(String username, boolean approval);
    List<Order> findAllByStockProductVendorUserUsernameAndPurchased(String username, boolean purchased);
    List<Order> findAllByUserUsernameAndBillStatus(String username, Status status);
}
