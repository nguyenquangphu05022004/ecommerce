package com.example.ecommerce.production.dal.repository.sku;

import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {
    List<ProductSku> findAllByProductSpuId(Long productSpuId);
}
