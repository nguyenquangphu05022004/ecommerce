package com.example.ecommerce.product.dal.repository.category;

import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
