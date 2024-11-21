package com.example.ecommerce.system.enums;

import com.example.ecommerce.common.exception.ErrorCode;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public interface ErrorCodeConstants {
    /**
     * ---------------------ROLE-------------
     */
    ErrorCode ROLE_NOT_FOUND = new ErrorCode("Role not found exception", NOT_FOUND.value());
    /**
     * -----------------MENU------------
     */
    ErrorCode MENU_NOT_FOUND = new ErrorCode("Menu not found exception", NOT_FOUND.value());
    /**
     * -----------------Authen----------------
     */
    ErrorCode USERNAME_NOT_FOUND = new ErrorCode("Username not found exception", NOT_FOUND.value());
    ErrorCode PASSWORD_NOT_FOUND = new ErrorCode("Password not found exception", NOT_FOUND.value());
    ErrorCode ACCESS_TOKEN_NOT_FOUND = new ErrorCode("Access token not found exception", NOT_FOUND.value());
    ErrorCode REFRESH_TOKEN_NOT_FOUND = new ErrorCode("Refresh token not found exception", NOT_FOUND.value());
}
