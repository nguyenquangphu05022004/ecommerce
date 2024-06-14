package com.example.ecommerce.exception;

public class ExpireCodeException extends RuntimeException{
    public ExpireCodeException(String field, String value) {
        super(String.format("%s ĐÃ HẾT HẠN có giá trị %s",field, value));

    }
}
