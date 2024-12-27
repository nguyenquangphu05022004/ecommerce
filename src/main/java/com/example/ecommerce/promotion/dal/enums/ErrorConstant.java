package com.example.ecommerce.promotion.dal.enums;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public interface ErrorConstant {
    ErrorCode COUPON_NOT_FOUND = new ErrorCode("Coupon not found", NOT_FOUND.value());
    ErrorCode DISCOUNT_ACTIVITY_NOT_FOUND = new ErrorCode("Discount activity not found", NOT_FOUND.value());
}
