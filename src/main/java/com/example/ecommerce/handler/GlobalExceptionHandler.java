package com.example.ecommerce.handler;

import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.OperationResponse;
import com.example.ecommerce.handler.exception.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = GeneralException.class)
    public APIResponse<?> handleGeneralException(GeneralException ex) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public APIResponse<OperationResponse> handleAuthenticationException(NotFoundException ex) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }

    @ExceptionHandler(value = AuthenticationFailureException.class)
    public APIResponse<?> handleAuthenticationException(AuthenticationFailureException ex) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }

    @ExceptionHandler(value = CodeExpiredException.class)
    public APIResponse<?> handleAuthenticationException(CodeExpiredException ex) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }

    @ExceptionHandler(value = MultipartException.class)
    public APIResponse<OperationResponse> handleParamFileException(MultipartException ex) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public APIResponse<?> handleUsernameNotFoundException(
            UsernameNotFoundException ex
    ) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }
    @ExceptionHandler(value = UserNameAlreadyExistsException.class)
    public APIResponse<?> handleUsernameNotFoundException(
            UserNameAlreadyExistsException ex
    ) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public APIResponse<?> handleValidationErrors(
            MethodArgumentNotValidException ex
    ) {
        return new APIResponse<>(ex.getMessage(), "0", 0, 400, null);

    }
}
