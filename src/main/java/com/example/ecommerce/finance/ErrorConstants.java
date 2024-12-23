package com.example.ecommerce.finance;

import com.example.ecommerce.frame.common.exception.ErrorCode;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public interface ErrorConstants {
    ErrorCode TRANSACTION_NOT_FOUND  = new ErrorCode("Transaction not found", NOT_FOUND.value());
    ErrorCode AMOUNT_NOT_ENOUGH = new ErrorCode("Amount in your wallet not enough", BAD_REQUEST.value());
    ErrorCode WALLET_NOT_FOUND = new ErrorCode("Wallet not found", BAD_REQUEST.value());
}
