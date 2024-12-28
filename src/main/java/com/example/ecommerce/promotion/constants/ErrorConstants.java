package com.example.ecommerce.promotion.constants;


import com.example.ecommerce.frame.common.exception.ErrorCode;
import com.example.ecommerce.promotion.dal.enums.ErrorConstant;

public interface ErrorConstants {
    ErrorCode DISCOUNT_NOT_FOUND = new ErrorCode("Discount not found", 404);
}
