package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.DiscountActivityCreateReqVO;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.PageDiscountActivityReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;

import java.util.List;

public interface DiscountActivityService {
    DiscountActivity createDiscountActivity(DiscountActivityCreateReqVO reqVO);
    List<DiscountActivity> getListDiscountActivityByUserId(Long userId);

    PageResult<DiscountActivity> getPageDiscountActivity(PageDiscountActivityReqVO req);

    DiscountActivity updateDiscountActivity(DiscountActivityCreateReqVO req);

    DiscountActivity getDiscountActivityById(Long id);
}
