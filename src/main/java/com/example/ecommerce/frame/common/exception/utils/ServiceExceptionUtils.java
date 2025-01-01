package com.example.ecommerce.frame.common.exception.utils;

import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.exception.ErrorCode;
import com.example.ecommerce.frame.common.exception.ExceptionMessage;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class ServiceExceptionUtils {
    private static Logger log = LoggerFactory.getLogger(ServiceExceptionUtils.class);
    public static ServiceException exception(ErrorCode errorCode) {
        log.warn("exception service occurred code[{}] message[{}]", errorCode.getCode(), errorCode.getMessage());
        return new ServiceException(errorCode.getMessage(), errorCode.getCode());
    }

    public static ServiceException exception(ErrorCode errorCode, Object...params)  {
        String messageException = errorCode.getMessage();
        Map<String, String> map = new HashMap<>();
        for(Object object : params) {
            ExceptionMessage exceptionMessage = object.getClass().getDeclaredAnnotation(ExceptionMessage.class);
            if(exceptionMessage != null) {
                String message = exceptionMessage.message();
                map.put(message, JsonUtils.write(object));
            }
        }
        if(!MapUtils.isEmpty(map)) {
            messageException = messageException + "\n" + JsonUtils.write(map);
        }
        return new ServiceException(messageException, errorCode.getCode());
    }


    public static ServiceException exception(String message, int code) {
        return new ServiceException(message, code);
    }

    public static ServiceException exception(String message, int code, Object... params) {
        return null;
    }

}
