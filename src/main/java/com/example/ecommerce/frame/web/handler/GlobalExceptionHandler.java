package com.example.ecommerce.frame.web.handler;

import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.frame.common.pojo.CommonResult;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public CommonResult<?> allExceptionHandler(HttpServletRequest request, Throwable ex) {
        if (ex instanceof MissingServletRequestParameterException) {
            return missingServletRequestParameterExceptionHandler((MissingServletRequestParameterException) ex);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            return methodArgumentTypeMismatchExceptionHandler((MethodArgumentTypeMismatchException) ex);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return methodArgumentNotValidExceptionExceptionHandler((MethodArgumentNotValidException) ex);
        }

        if (ex instanceof ConstraintViolationException) {
            return constraintViolationExceptionHandler((ConstraintViolationException) ex);
        }
        if (ex instanceof ValidationException) {
            return validationException((ValidationException) ex);
        }
        if (ex instanceof ServiceException) {
            return serviceExceptionHandler((ServiceException) ex);
        }
        if (ex instanceof AccessDeniedException) {
            return accessDeniedExceptionHandler(request, (AccessDeniedException) ex);
        }
        return defaultExceptionHandler(request, ex);
    }

    /**
     * Xu ly validation trong dto
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
         log.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return CommonResult.error(BAD_REQUEST.value(), String.format("Require parameters invalid: %s", constraintViolation.getMessage()));
    }
    @ExceptionHandler(ValidationException.class)
    public CommonResult<?> validationException(ValidationException ex) {
        log.warn("[validationException]", ex);
        return CommonResult.error(BAD_REQUEST.value());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<?> methodArgumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException ex) {
        log.warn("[methodArgumentNotValidExceptionExceptionHandler]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null : "Field should not be null";
        return CommonResult.error(BAD_REQUEST.value(), String.format("Parameter invalid: %s", fieldError.getDefaultMessage()));
    }

    /**
     * Xu ly trong service
     * @param ex
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public CommonResult<?> serviceExceptionHandler(ServiceException ex) {
        log.warn("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * Xu ly khi user khong co quyen truy cap den
     * tai nguyen cu the
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult<?> accessDeniedExceptionHandler(HttpServletRequest request, AccessDeniedException ex) {
        log.warn("[accessDeniedExceptionHandler][userId({}) access url({})", SecurityUtils.getLoginUserMemberId(), request.getRequestURL(), ex);
        return CommonResult.error(FORBIDDEN.value());
    }

    /**
     * exception mac dinh
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResult<?> defaultExceptionHandler(HttpServletRequest request, Throwable ex) {
        return null;
    }

    /**
     * Xy ly tham so trong controller: chuyen doi
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public CommonResult<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException ex) {
        log.warn("[methodArgumentTypeMismatchExceptionHandler]", ex);
        return CommonResult.error(BAD_REQUEST.value(), String.format("Type of param invalid: %s", ex.getMessage()));
    }

    /**
     * Xu ly thieu tham so
     * @param ex
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public CommonResult<?> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        log.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(BAD_REQUEST.value(), String.format("Missing parameter: %s", ex.getParameterName()));
    }
}
