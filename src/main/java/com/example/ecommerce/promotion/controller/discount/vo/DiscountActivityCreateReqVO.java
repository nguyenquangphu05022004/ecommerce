package com.example.ecommerce.promotion.controller.discount.vo;

import com.example.ecommerce.promotion.dal.enums.CommonStatusTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DiscountActivityCreateReqVO {
    private String activityName;
    private LocalDateTime activityStartTime;
    private LocalDateTime activityEndTime;
}
