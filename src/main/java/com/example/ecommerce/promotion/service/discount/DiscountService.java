package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.promotion.controller.admin.discount.vo.self.DiscountCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;

import java.util.List;

public interface DiscountService {
    Discount createDiscount(DiscountCreateReqVO reqVO);
    List<Discount> getDiscountBySpuId(Long spuId);
    List<Discount> getListDiscount(Long userId);

    void deleteById(Long id);
    Discount getDiscountById(Long id);
    Discount updateDiscount(DiscountCreateReqVO req);
}
