package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.product.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface BrandRepository extends JpaRepository<ProductBrand, Long> {

    void deleteByName(String name);
}
