package com.example.ecommerce.production.dal.repository.property;

import com.example.ecommerce.production.dal.dataobject.properties.ProductPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValue, Long> {
}
