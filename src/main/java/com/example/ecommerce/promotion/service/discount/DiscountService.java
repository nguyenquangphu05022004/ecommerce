package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.promotion.controller.discount.vo.DiscountCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;

import java.util.List;

public interface DiscountService {
    Discount createDiscount(DiscountCreateReqVO reqVO);
    List<Discount> getDiscountBySpuId(Long spuId);
    List<Discount> getListDiscount(Long userId);
}
