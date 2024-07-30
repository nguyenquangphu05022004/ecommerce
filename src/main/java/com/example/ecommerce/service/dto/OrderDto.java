package com.example.ecommerce.service.dto;

import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.entities.order.OrderStatus;
import com.example.ecommerce.domain.entities.auth.UserContactDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;
@Getter
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@NoArgsConstructor
public class OrderDto extends BaseDto {
    private UserContactDetails userContactDetails;
    private Set<LineItemDto> lineItems;
    private Order.Payment payment;
    private Integer priceCoupon;
    private OrderStatus orderStatus;
    private boolean approval;
    private boolean purchased;
    private boolean received;
}
