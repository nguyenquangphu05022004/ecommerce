package com.example.ecommerce.handler;

import com.example.ecommerce.common.enums.CustomStatusCode;
import com.example.ecommerce.handler.exception.AuthenticationFailureException;
import com.example.ecommerce.handler.exception.CodeExpiredException;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.NotFoundException;
import com.example.ecommerce.service.response.APIResponse;
import com.example.ecommerce.service.response.OperationResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<OperationResponse> handleGeneralException(GeneralException ex) {
        return new ResponseEntity<>(
                new OperationResponse(false, ex.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public APIResponse<?> handleAuthenticationException(NotFoundException ex) {
        return new APIResponse<>(
                "Not found object that you are finding",
                ex.getMessage(),
                0,
               CustomStatusCode.NOT_FOUND.getNumber(),
                null
        );
    }

    @ExceptionHandler(value = AuthenticationFailureException.class)
    public APIResponse<?> handleAuthenticationException(AuthenticationFailureException ex) {
        return new APIResponse<>(
                "Authentication failure",
                ex.getMessage(),
                0,
                HttpStatus.BAD_REQUEST.value(),
                null
        );
    }

    @ExceptionHandler(value = CodeExpiredException.class)
    public APIResponse<?> handleAuthenticationException(CodeExpiredException ex) {
        return new APIResponse<>(
                "Code expired",
                ex.getMessage(),
                0,
                CustomStatusCode.CODE_EXPIRED.getNumber(),
                null
        );
    }

    @ExceptionHandler(value = MultipartException.class)
    public ResponseEntity<OperationResponse> handleParamFileException(MultipartException ex) {
        return new ResponseEntity<>(
                new OperationResponse(false, "Your request must pass a file in that"),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleUsernameNotFoundException(
            UsernameNotFoundException ex
    ) {
        return new ResponseEntity<>(
                getErrorsMap(Collections.singletonList(ex.getMessage())),
                new HttpHeaders(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return new ResponseEntity<>(
                getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST
        );
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("errors", errors);
        return map;
    }
}
