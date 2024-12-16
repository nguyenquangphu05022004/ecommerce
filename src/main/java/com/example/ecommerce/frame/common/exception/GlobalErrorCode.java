package com.example.ecommerce.frame.common.exception;

public interface GlobalErrorCode {
    ErrorCode SUCCESS = new ErrorCode("Thanh cong", 200);
    ErrorCode INTERNAL_ERROR = new ErrorCode("Xay ra loi trong he thong", 100_000_000);
}
