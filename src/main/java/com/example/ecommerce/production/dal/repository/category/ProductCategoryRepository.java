package com.example.ecommerce.production.dal.repository.category;

import com.example.ecommerce.production.dal.dataobject.category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
