package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.auth.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByUserUsername(String username);

}
