package com.example.ecommerce.product.dal.repository.spu;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSpuRepository extends JpaRepository<ProductSpu, Long>, JpaSpecificationExecutor<ProductSpu> {
    Page<ProductSpu> findAllBySellerId(Long sellerId, Pageable pageable);
}
