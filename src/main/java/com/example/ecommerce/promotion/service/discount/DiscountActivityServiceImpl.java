package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.promotion.controller.discount.vo.DiscountActivityCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import com.example.ecommerce.promotion.dal.repo.discount.DiscountActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscountActivityServiceImpl implements DiscountActivityService{
    private final DiscountActivityRepository discountActivityRepository;
    @Override
    public DiscountActivity createDiscountActivity(DiscountActivityCreateReqVO reqVO) {
        DiscountActivity discountActivity = DiscountActivity.builder().activityName(reqVO.getActivityName())
                .activityEndTime(reqVO.getActivityEndTime())
                .activityStartTime(reqVO.getActivityStartTime()).build();
        this.discountActivityRepository.save(discountActivity);
        return discountActivity;
    }

    @Override
    public List<DiscountActivity> getListDiscountActivityByUserId(Long userId) {
        return this.discountActivityRepository.findAllByCreatedBy(userId);
    }

}
