package com.example.ecommerce.promotion.dal.repo.discount;

import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
    List<Discount> findAllByProductSpuId(Long spuId);
    List<Discount> findAllByCreatedBy(Long userId);
}
