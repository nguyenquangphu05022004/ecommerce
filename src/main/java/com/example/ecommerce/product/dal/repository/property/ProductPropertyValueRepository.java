package com.example.ecommerce.product.dal.repository.property;

import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValue, Long> {
    List<ProductPropertyValue> findAllByProductPropertyId(Long propertyId);
}
