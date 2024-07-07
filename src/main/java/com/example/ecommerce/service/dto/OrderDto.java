package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.domain.dto.BaseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;
@Getter
@SuperBuilder(toBuilder = true)
public class OrderDto extends BaseDto {
    private UserContactDetails userContactDetails;
    private Set<LineItemDto> lineItems;
    private Payment payment;
    private Coupon coupon;
    private Status status;
    private boolean approval;
    private boolean purchased;
    private boolean received;
}
