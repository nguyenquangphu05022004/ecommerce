package com.example.ecommerce.system.enums;

import com.example.ecommerce.frame.common.exception.ErrorCode;

import static org.springframework.http.HttpStatus.*;

public interface SysErrorCodeConstants {
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
    ErrorCode ACCOUNT_IS_LOCKED = new ErrorCode("Account is locked", NOT_ACCEPTABLE.value());
    ErrorCode USERNAME_NOT_FOUND = new ErrorCode("Username not found exception", NOT_FOUND.value());
    ErrorCode PASSWORD_NOT_FOUND = new ErrorCode("Password not found exception", NOT_FOUND.value());
    ErrorCode ACCESS_TOKEN_NOT_FOUND = new ErrorCode("Access token not found exception", NOT_FOUND.value());
    ErrorCode REFRESH_TOKEN_NOT_FOUND = new ErrorCode("Refresh token not found exception", NOT_FOUND.value());


    /**
     * --------------------User-------------
     */
    ErrorCode USER_NOT_FOUND = new ErrorCode("User not found", NOT_FOUND.value());
    ErrorCode USER_ADDRESS_NOT_FOUND = new ErrorCode("Please enter your address", NOT_FOUND.value());

    /**
     * Mail
     */
    ErrorCode MAIL_LOG_NOT_FOUND = new ErrorCode("MailLog not foun", NOT_FOUND.value());
    ErrorCode MAIL_ACCOUNT_NOT_FOUND = new ErrorCode("MailAccount not found", NOT_FOUND.value());
    ErrorCode USER_HAS_NOT_CREATEd_MAIL_ACCOUNT = new ErrorCode("User has not created mailaccount", BAD_REQUEST.value());
    ErrorCode MAIL_TEMPLATE_NOT_FOUND = new ErrorCode("MailTemplate not found", NOT_FOUND.value());
    ErrorCode MAIL_TEMPLATE_PARAMS_MISSING_KEY = new ErrorCode("MailTemplate missing key", BAD_REQUEST.value());
    /**
     * Notify
     */
    ErrorCode NOTIFY_TEMPLATE_NOT_FOUND = new ErrorCode("NotifyTemplate not found", NOT_FOUND.value());
    ErrorCode NOTIFY_TEMPLATE_PARAMS_MISSING_KEY = new ErrorCode("NotifyTemplate params missing key", NOT_FOUND.value());
    ErrorCode NOTIFY_MESSAGE_NOT_FOUND = new ErrorCode("NotifyMessage not found", NOT_FOUND.value());
    ErrorCode UPDATE_NOTIFY_MESSAGE_DENIED = new ErrorCode("Update NotifyMessage denied", FORBIDDEN.value());
}
