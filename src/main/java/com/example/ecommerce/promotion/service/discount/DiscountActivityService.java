package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.promotion.controller.discount.vo.DiscountActivityCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;

import java.util.List;

public interface DiscountActivityService {
    DiscountActivity createDiscountActivity(DiscountActivityCreateReqVO reqVO);
    List<DiscountActivity> getListDiscountActivityByUserId(Long userId);
}
