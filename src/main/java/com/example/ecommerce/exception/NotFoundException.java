package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException(String field, String value) {
        super(String.format("KHÔNG TÌM THẤY %s VỚI GIÁ TRỊ: %s TRONG CƠ SỞ DỮ LIỆU",field, value));
    }
}
