package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotValidException extends RuntimeException{
    public NotValidException(String field, String value) {
        super(String.format("%s VỚI GIÁ TRỊ: %s KHÔNG PHÙ HỢP TRONG CƠ SỞ DỮ LIỆU",field, value));
    }
}