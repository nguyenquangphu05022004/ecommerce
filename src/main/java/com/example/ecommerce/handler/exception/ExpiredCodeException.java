package com.example.ecommerce.handler.exception;

public class ExpiredCodeException extends RuntimeException{
    public ExpiredCodeException(String message) {
        super(message);
    }
}
