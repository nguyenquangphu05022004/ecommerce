package com.example.ecommerce.system.dal.repository.logger;

import com.example.ecommerce.system.dal.dataobject.logger.OperationLogger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationLoggerRepository extends JpaRepository<OperationLogger, Long> {
}
