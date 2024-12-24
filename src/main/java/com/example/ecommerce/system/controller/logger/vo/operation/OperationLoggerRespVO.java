package com.example.ecommerce.system.controller.logger.vo.operation;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.operatelog.enums.OperationType;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import lombok.Getter;

@Getter
public class OperationLoggerRespVO {
    private Long id;
    private String username;
    private String ipAddress;
    private String userAgent;

    private OperationType operationType;
    private String methodName;
    private String params;

    private String returnMessage;
    private Integer returnCode;
    private String returnResult;

    private String createdDate;

    public OperationLoggerRespVO(OperationLogger operationLogger) {
        this.id = operationLogger.getId();
        this.username = operationLogger.getUsername();
        this.ipAddress = operationLogger.getIpAddress();
        this.userAgent = operationLogger.getUserAgent();
        this.operationType = operationLogger.getOperationType();
        this.methodName = operationLogger.getMethodName();
        this.params = operationLogger.getParams();
        this.returnMessage = operationLogger.getReturnMessage();
        this.returnCode = operationLogger.getReturnCode();
        this.returnResult = operationLogger.getReturnResult();
        this.createdDate = DateTimeUtils.format(operationLogger.getCreatedDate());
    }
}
