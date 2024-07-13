package com.example.ecommerce.handler;

import com.example.ecommerce.handler.exception.AuthenticationFailureException;
import com.example.ecommerce.handler.exception.GeneralException;
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
    @ExceptionHandler(value = AuthenticationFailureException.class)
    public ResponseEntity<OperationResponse> handleAuthenticationException(AuthenticationFailureException ex) {
        return new ResponseEntity<>(
                new OperationResponse(false, ex.getMessage()),
                HttpStatus.BAD_REQUEST
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
