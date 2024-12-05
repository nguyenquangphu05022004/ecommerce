package com.example.ecommerce.product.dal.repository.property;

import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {
}
