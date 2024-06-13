package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByUserUsername(String username);

    @Query("select v from Vendor v inner join User u on v.user.id = u.id " +
            "where u.username != :username and v.shopName like %:vendorName%")
    List<Vendor> findAllByVendorNameAndNotMySelf(String vendorName, String username);
}
