package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.Coupon;
import com.example.ecommerce.domain.Payment;
import com.example.ecommerce.domain.Status;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.dto.BaseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;
@Getter
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto {
    private UserContactDetails userContactDetails;
    private Set<LineItemDto> lineItems;
    private Payment payment;
    private Integer priceCoupon;
    private Status status;
    private boolean approval;
    private boolean purchased;
    private boolean received;
}
