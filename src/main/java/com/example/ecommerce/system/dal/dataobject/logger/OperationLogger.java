package com.example.ecommerce.system.dal.dataobject.logger;

import com.example.ecommerce.frame.auditting.BaseEntity;
import com.example.ecommerce.frame.operatelog.enums.OperationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "sys_logger_operation")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OperationLogger extends BaseEntity {
    private String username;
    private String ipAddress;
    private String userAgent;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    private String methodName;
    private String params;

    private String returnMessage;
    private Integer returnCode;
    private String returnResult;
}
