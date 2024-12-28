package com.example.ecommerce.statistic.dal.repo.product;

import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductStatisticRepository extends JpaRepository<ProductStatistic, Long> {
    Optional<ProductStatistic> findByProductSpuId(Long productSpuId);
}
