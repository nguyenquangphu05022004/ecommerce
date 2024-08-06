package com.example.ecommerce.handler;

import com.example.ecommerce.handler.exception.*;
import com.example.ecommerce.service.response.OperationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<OperationResponse> handleGeneralException(GeneralException ex) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<OperationResponse> handleAuthenticationException(NotFoundException ex) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(value = AuthenticationFailureException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationFailureException ex) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(value = CodeExpiredException.class)
    public ResponseEntity<?> handleAuthenticationException(CodeExpiredException ex) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(value = MultipartException.class)
    public ResponseEntity<OperationResponse> handleParamFileException(MultipartException ex) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(
            UsernameNotFoundException ex
    ) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }
    @ExceptionHandler(value = UserNameAlreadyExistsException.class)
    public ResponseEntity<?> handleUsernameNotFoundException(
            UserNameAlreadyExistsException ex
    ) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        return ResponseEntity.ok(
                new OperationResponse(false, ex.getMessage(), HttpStatus.BAD_REQUEST.value())
        );
    }
}
