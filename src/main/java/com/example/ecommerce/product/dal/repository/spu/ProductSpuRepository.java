package com.example.ecommerce.product.dal.repository.spu;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSpuRepository extends JpaRepository<ProductSpu, Long> {
    Page<ProductSpu> findAllBySellerUserMemberId(Long userMemberId, Pageable pageable);
}
