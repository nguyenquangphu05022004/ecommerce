package com.example.ecommerce.production.dal.repository.property;

import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPropertyRepository extends JpaRepository<ProductProperty, Long> {
}
