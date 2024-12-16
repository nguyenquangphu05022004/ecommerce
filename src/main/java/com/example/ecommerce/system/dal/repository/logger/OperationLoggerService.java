package com.example.ecommerce.system.dal.repository.logger;

import com.example.ecommerce.frame.operatelog.dto.OperationLoggerDto;

public interface OperationLoggerService {

    void clear(Long[] ids);
    void createOperationLogger(OperationLoggerDto dto);
    void getPageOperationLogger(int page);

}
