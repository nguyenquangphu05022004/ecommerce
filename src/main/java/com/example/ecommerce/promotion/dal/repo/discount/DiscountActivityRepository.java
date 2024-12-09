package com.example.ecommerce.promotion.dal.repo.discount;

import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountActivityRepository extends JpaRepository<DiscountActivity, Long> {
    List<DiscountActivity> findAllByCreatedBy(Long createdBy);
}
