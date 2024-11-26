package com.example.ecommerce.production.dal.repository.sku;

import com.example.ecommerce.production.dal.dataobject.sku.ProductSkuProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSkuPropertyRepository extends JpaRepository<ProductSkuProperty, Long> {
}
