package com.example.ecommerce.frame.operatelog.dto;

import com.example.ecommerce.frame.operatelog.enums.OperationType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class OperationLoggerDto {
    private String username;
    private String ipAddress;
    private String userAgent;

    private OperationType operationType;
    private String methodName;
    private String params;

    private String returnMessage;
    private Integer returnCode;
    private String returnResult;


    public OperationLoggerDto setUsername(String username) {
        this.username = username;return this;
    }

    public OperationLoggerDto setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;return this;
    }

    public OperationLoggerDto setUserAgent(String userAgent) {
        this.userAgent = userAgent;return this;
    }

    public OperationLoggerDto setOperationType(OperationType operationType) {
        this.operationType = operationType;return this;
    }

    public OperationLoggerDto setMethodName(String methodName) {
        this.methodName = methodName;return this;
    }

    public OperationLoggerDto setParams(String params) {
        this.params = params;return this;
    }

    public OperationLoggerDto setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;return this;
    }

    public OperationLoggerDto setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;return this;
    }

    public OperationLoggerDto setReturnResult(String returnResult) {
        this.returnResult = returnResult; return this;
    }
}
