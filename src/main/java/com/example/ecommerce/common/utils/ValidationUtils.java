package com.example.ecommerce.common.utils;


import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.service.dto.BaseDto;

import java.util.Collection;

public class ValidationUtils {
    private static final String CAN_NOT_BE_EMPTY = "%s can not be empty or null";
    public static void fieldCheckNullOrEmpty(String value, String field) {
        if(value == null || value.isEmpty() || value.isBlank()) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY, field));
        }
    }
    public static void fieldCheckNullOrEmpty(Number value, String field) {
        if(value == null) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY, field));
        }
    }
    public static void fieldCheckNullOrEmpty(BaseDto baseDto, String className) {
        if(baseDto == null) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY, className));
        }
    }
    public static int fieldCheckNullOrEmpty(Collection<?> list) {
        if(list == null) {
            return 0;
        }
        return list.size();
    }

}
