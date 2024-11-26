package com.example.ecommerce.system.dal.repository.user;

import com.example.ecommerce.system.dal.dataobject.user.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
