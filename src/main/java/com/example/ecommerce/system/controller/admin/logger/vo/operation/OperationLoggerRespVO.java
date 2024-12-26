package com.example.ecommerce.system.controller.admin.logger.vo.operation;

import com.example.ecommerce.frame.common.date.DateTimeUtils;
import com.example.ecommerce.frame.operatelog.enums.OperationType;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class OperationLoggerRespVO {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "username cua user")
    private String username;
    @Schema(description = "ip cua user")
    private String ipAddress;
    @Schema(description = "thiet bi dau cuoi user")
    private String userAgent;

    @Schema(description = "Loai thao tac cua user")
    private OperationType operationType;
    @Schema(description = "Ten phuong thuc hoat dong")
    private String methodName;

    @Schema(description = "Tham so trong phuong thuc")
    private String params;

    @Schema(description = "message tra ve")
    private String returnMessage;
    @Schema(description = "Code")
    private Integer returnCode;

    @Schema(description = "Ket qua tra ve")
    private String returnResult;

    @Schema(description = "Ngay truy cap")
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
