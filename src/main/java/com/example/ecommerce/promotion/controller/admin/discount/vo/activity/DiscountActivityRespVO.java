package com.example.ecommerce.promotion.controller.admin.discount.vo.activity;

import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class DiscountActivityRespVO {
    private Long id;
    private String activityName;
    private LocalDateTime activityStartTime;
    private LocalDateTime activityEndTime;
    public DiscountActivityRespVO(DiscountActivity activity) {
        this.id = activity.getId();
        this.activityName = activity.getActivityName();
        this.activityStartTime = activity.getActivityStartTime();
        this.activityEndTime = activity.getActivityEndTime();
    }
}
