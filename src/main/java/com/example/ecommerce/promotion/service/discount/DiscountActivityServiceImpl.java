package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.DiscountActivityCreateReqVO;
import com.example.ecommerce.promotion.controller.admin.discount.vo.activity.PageDiscountActivityReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import com.example.ecommerce.promotion.dal.repo.discount.DiscountActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.promotion.dal.enums.ErrorConstant.DISCOUNT_ACTIVITY_NOT_FOUND;

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

    @Override
    public PageResult<DiscountActivity> getPageDiscountActivity(PageDiscountActivityReqVO req) {
        return null;
    }

    @Override
    public DiscountActivity updateDiscountActivity(DiscountActivityCreateReqVO req) {
        return null;
    }

    @Override
    public DiscountActivity getDiscountActivityById(Long id) {
        return this.discountActivityRepository.findById(id)
                .orElseThrow(() -> exception(DISCOUNT_ACTIVITY_NOT_FOUND));
    }

}
