package com.example.ecommerce.service;

public interface VerifyService {
    boolean update(String code, String password);

    boolean isCodeExpire(String code);
}
