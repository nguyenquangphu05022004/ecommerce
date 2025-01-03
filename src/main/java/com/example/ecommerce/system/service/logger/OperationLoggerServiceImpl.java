package com.example.ecommerce.system.service.logger;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.operatelog.dto.OperationLoggerDto;
import com.example.ecommerce.system.controller.admin.logger.vo.operation.PageOperationLoggerReqVO;
import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import com.example.ecommerce.system.dal.repository.logger.OperationLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationLoggerServiceImpl implements OperationLoggerService{
    private final OperationLoggerRepository operationLoggerRepository;
    @Override
    public void clear(Collection<Long> ids) {
        if(!CollUtils.isEmpty(ids)) {
            ids.forEach(id -> {
                this.operationLoggerRepository.deleteById(id);
            });
        }
    }

    @Override
    public void createOperationLogger(OperationLoggerDto dto) {

        OperationLogger operationLogger = OperationLogger.builder()
                .userAgent(dto.getUserAgent()).username(dto.getUsername())
                .operationType(dto.getOperationType()).ipAddress(dto.getIpAddress())
                .methodName(dto.getMethodName()).params(dto.getParams())
                .returnCode(dto.getReturnCode()).returnMessage(dto.getReturnMessage())
                .returnResult(dto.getReturnResult())
                .build();
        this.operationLoggerRepository.save(operationLogger);
    }

    @Override
    public PageResult<OperationLogger> getPageOperationLog(PageOperationLoggerReqVO page) {
        return null;
    }

    @Override
    public List<OperationLogger> getListOperationLog() {
        return operationLoggerRepository.findAll();
    }
}
