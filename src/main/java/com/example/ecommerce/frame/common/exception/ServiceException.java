package com.example.ecommerce.frame.common.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{
    private String message;
    private int code;

    public ServiceException(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
