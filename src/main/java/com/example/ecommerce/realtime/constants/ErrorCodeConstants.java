package com.example.ecommerce.realtime.constants;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FOUND;

public interface ErrorCodeConstants {
    ErrorCode MESSAGE_NOT_FOUND = new ErrorCode("Message not found", FOUND.value());
    ErrorCode LIVE_STREAM_NOT_FOUND = new ErrorCode("Livestream not found", FOUND.value());
    ErrorCode LIVE_COMMENT_NOT_FOUND = new ErrorCode("LiveComment not found", FOUND.value());
    ErrorCode LIVE_PRODUCT_NOT_FOUND = new ErrorCode("LiveProduct not found", FOUND.value());
    ErrorCode CHAT_BETWEEN_USER_IS_NOT_ESTABLISHED = new ErrorCode("Chat between user is not established", FOUND.value());
    ErrorCode MESSAGE_TEMPLATE_NOT_FOUND = new ErrorCode("Message template not found", FOUND.value());
}
