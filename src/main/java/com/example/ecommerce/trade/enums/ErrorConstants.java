package com.example.ecommerce.trade.enums;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public interface ErrorConstants {
    ErrorCode CART_NOT_FOUND = new ErrorCode("Cart not found", NO_CONTENT.value());
    ErrorCode ORDER_NOT_FOUND = new ErrorCode("Order not found", NO_CONTENT.value());
    ErrorCode ORDER_APPROVAL = new ErrorCode("Order is approval, you can't cancel, if you want do it please contact the shop", BAD_REQUEST.value());
    ErrorCode COUPON_EXPIRED = new ErrorCode("Coupon is expired", BAD_REQUEST.value());
    ErrorCode COUPON_UNUSED = new ErrorCode("Coupon start time has not yet arrived", BAD_REQUEST.value());
    ErrorCode COUPON_IS_REVOKED = new ErrorCode("Coupon is revoked", BAD_REQUEST.value());
}
