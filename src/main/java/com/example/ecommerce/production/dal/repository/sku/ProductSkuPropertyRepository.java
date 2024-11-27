package com.example.ecommerce.production.dal.repository.sku;

import com.example.ecommerce.production.dal.dataobject.sku.ProductSkuProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSkuPropertyRepository extends JpaRepository<ProductSkuProperty, Long> {
    List<ProductSkuProperty> findAllByProductSkuId(Long productSkuId);
}
