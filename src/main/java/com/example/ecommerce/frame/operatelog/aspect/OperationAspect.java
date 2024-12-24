package com.example.ecommerce.frame.operatelog.aspect;

import com.example.ecommerce.frame.common.exception.ErrorCode;
import com.example.ecommerce.frame.common.exception.GlobalErrorCode;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.common.servlet.ServletUtils;
import com.example.ecommerce.frame.operatelog.annotation.OperationLog;
import com.example.ecommerce.frame.operatelog.dto.OperationLoggerDto;
import com.example.ecommerce.frame.operatelog.enums.OperationType;
import com.example.ecommerce.system.service.logger.OperationLoggerService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.ecommerce.frame.security.core.utils.SecurityUtils.getLoginUserUserMemberUsername;

@Aspect
@Component
@RequiredArgsConstructor
public class OperationAspect {

    private OperationLoggerService operationLoggerService;

    @Around("@annotation(operationLog)")
    public Object operationLog(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        try {
            Object result = joinPoint.proceed();
            writeLog(joinPoint,operationLog,  result, null);
            return result;
        }
        catch (Throwable e) {
            writeLog(joinPoint, operationLog, null, e);
            throw e;
        }
    }


    private void writeLog(ProceedingJoinPoint joinPoint, OperationLog operationLog,
                          Object result, Throwable exception) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramsName = signature.getParameterNames();
        Object[] paramsValue = joinPoint.getArgs();

        OperationLoggerDto operationLoggerDto = new OperationLoggerDto()
                .setIpAddress(ServletUtils.getIpAddress()).setUserAgent(ServletUtils.getUserAgent())
                .setUsername(getLoginUserUserMemberUsername()).setMethodName(signature.getName())
                .setOperationType(obtainOperation(joinPoint, operationLog));

        if(operationLog.logArgs()) {
            operationLoggerDto.setParams(writeParams(paramsName, paramsValue));
        }
        if(result != null) {
            if(operationLog.logResults()) {
                if(result instanceof CommonResult<?>) {
                    CommonResult<?> commonResult = (CommonResult<?>)result;
                    operationLoggerDto.setReturnResult(JsonUtils.write(commonResult.getData()))
                            .setReturnCode(commonResult.getCode()).setReturnMessage(commonResult.getMessage());
                } else {
                    ErrorCode success = GlobalErrorCode.SUCCESS;
                    operationLoggerDto.setReturnResult(JsonUtils.write(result))
                            .setReturnCode(success.getCode()).setReturnMessage(success.getMessage());
                }
            }
        }

        if(exception != null) {
            ErrorCode errorCode = GlobalErrorCode.INTERNAL_ERROR;
            String convertMessage = String.format("[%s]:[%s]", errorCode.getMessage(), exception.getMessage());
            operationLoggerDto.setReturnMessage(convertMessage).setReturnCode(errorCode.getCode());
        }

        this.operationLoggerService.createOperationLogger(operationLoggerDto);

    }

    private OperationType obtainOperation(ProceedingJoinPoint joinPoint, OperationLog operationLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RequestMapping annotation = AnnotationUtils.getAnnotation(signature.getMethod(), RequestMapping.class);
        if(annotation == null) {
            return operationLog.operationType();
        }
        return switch (annotation.method()[0]) {
            case GET -> OperationType.GET;
            case POST -> OperationType.CREATE;
            case PUT -> OperationType.UPDATE;
            case DELETE -> OperationType.DELETE;
            default -> OperationType.OTHER;
        };
    }


    private String writeParams(String[] paramsName, Object[] paramsValue) {
        return null;
    }
}
