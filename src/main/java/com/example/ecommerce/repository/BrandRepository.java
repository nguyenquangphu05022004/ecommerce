package com.example.ecommerce.repository;

import com.example.ecommerce.domain.entities.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<ProductBrand, Long> {

    void deleteByName(String name);
}
