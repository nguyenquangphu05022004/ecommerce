package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Bill;
import com.example.ecommerce.domain.dto.ENUM.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findAllByStatus(Status status);
//    List<Bill> findAllByOrderProductVendorUserUsername(String username);
//    boolean existsByOrderBillStatusAndOrderProductIdAndOrderUserUsername(Status status, Long productId, String username);
}
