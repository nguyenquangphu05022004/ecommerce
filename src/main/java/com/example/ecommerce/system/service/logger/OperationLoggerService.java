package com.example.ecommerce.system.service.logger;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.operatelog.dto.OperationLoggerDto;
import com.example.ecommerce.system.controller.logger.vo.operation.PageOperationLoggerReqVO;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;

import java.util.Collection;
import java.util.List;

public interface OperationLoggerService {

    void clear(Collection<Long> ids);
    void createOperationLogger(OperationLoggerDto dto);
    PageResult<OperationLogger> getPageOperationLog(PageOperationLoggerReqVO page);
    List<OperationLogger> getListOperationLog();
}
