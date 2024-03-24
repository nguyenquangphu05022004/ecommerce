package com.example.ecommerce.service;

import com.example.ecommerce.entity.Verify;

public interface VerifyService {
    boolean update(String code, String password);

    boolean isCodeExpire(String code);
}
