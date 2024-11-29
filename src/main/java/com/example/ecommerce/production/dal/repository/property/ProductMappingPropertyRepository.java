package com.example.ecommerce.production.dal.repository.property;

import com.example.ecommerce.production.dal.dataobject.properties.ProductMappingProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductMappingPropertyRepository extends JpaRepository<ProductMappingProperty, Long> {
}
