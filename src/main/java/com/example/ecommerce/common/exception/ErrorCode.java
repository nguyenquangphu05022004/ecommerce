package com.example.ecommerce.common.exception;

import lombok.Data;

@Data
public class ErrorCode {
    private String message;
    private int code;

    public ErrorCode(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
