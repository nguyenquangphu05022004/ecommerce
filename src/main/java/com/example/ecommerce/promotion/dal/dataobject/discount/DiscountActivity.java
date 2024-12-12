package com.example.ecommerce.promotion.dal.dataobject.discount;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.promotion.dal.enums.CommonStatusTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder(toBuilder = true)
@Entity
@Table(name = "promotion_discount_activity")
@Getter
@Setter
@NoArgsConstructor
public class DiscountActivity extends BaseEntity {
    private String activityName;
    private LocalDateTime activityStartTime;
    private LocalDateTime activityEndTime;
}
