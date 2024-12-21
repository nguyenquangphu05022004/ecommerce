package com.example.ecommerce.realtime.constants;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FOUND;

public interface ErrorCodeConstants {
    ErrorCode MESSAGE_NOT_FOUND = new ErrorCode("Message not found", FOUND.value());
}
