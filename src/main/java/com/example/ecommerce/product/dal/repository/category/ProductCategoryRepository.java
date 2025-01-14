package com.example.ecommerce.product.dal.repository.category;

import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
}
