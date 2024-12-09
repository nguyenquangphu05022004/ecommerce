package com.example.ecommerce.promotion.dal.enums;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public interface ErrorConstant {
    ErrorCode COUPON_NOT_FOUND = new ErrorCode("Coupon not found", HttpStatus.NOT_FOUND.value());
}
