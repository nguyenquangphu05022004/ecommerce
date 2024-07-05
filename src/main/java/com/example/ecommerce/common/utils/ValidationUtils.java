package com.example.ecommerce.common.utils;


import com.example.ecommerce.handler.exception.GeneralException;

public class ValidationUtils {
    private static final String CAN_NOT_BE_EMPTY = "%s can not be empty";
    public static void fieldCheckNullOrEmpty(String value, String field) {
        if(value.isEmpty() || value.isBlank()) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY, field));
        }
    }
    public static void fieldCheckNullOrEmpty(Integer value, String field) {
        if(value == null) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY, field));
        }
    }
}
