package com.example.ecommerce.common.exception.utils;

import com.example.ecommerce.common.exception.ErrorCode;
import com.example.ecommerce.common.exception.ServiceException;

public class ServiceExceptionUtils {

    public static ServiceException exception(ErrorCode errorCode) {
        return new ServiceException(errorCode.getMessage(), errorCode.getCode());
    }

    public static ServiceException exception(String message, int code) {
        return new ServiceException(message, code);
    }

    public static ServiceException exception(String message, int code, Object... params) {
        return null;
    }

}
