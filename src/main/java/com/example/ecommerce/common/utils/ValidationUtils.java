package com.example.ecommerce.common.utils;


import com.example.ecommerce.domain.dto.BaseDto;
import com.example.ecommerce.handler.exception.GeneralException;
import org.springframework.web.multipart.MultipartFile;

public class ValidationUtils {
    private static final String CAN_NOT_BE_EMPTY = "%s can not be empty or null";
    public static void fieldCheckNullOrEmpty(String value, String field) {
        if(value.isEmpty() || value.isBlank()) {
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
    public static void fieldCheckNullOrEmpty(MultipartFile file) {
        if(file == null) {
            throw new GeneralException(String.format(CAN_NOT_BE_EMPTY, "file upload not null"));
        }
    }
}
