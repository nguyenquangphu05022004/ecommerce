package com.example.ecommerce.trade.enums;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public interface ErrorConstants {
    ErrorCode CART_NOT_FOUND = new ErrorCode("Cart not found", HttpStatus.NO_CONTENT.value());
}
