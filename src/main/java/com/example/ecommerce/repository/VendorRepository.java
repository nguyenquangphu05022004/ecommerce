package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
