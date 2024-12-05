package com.example.ecommerce.product.dal.repository.spu;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSpuDetailRepository extends JpaRepository<ProductSpuDetail, Long> {
    List<ProductSpuDetail> findAllByProductSpuId(Long spuId);
}
