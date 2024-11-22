//package com.example.ecommerce.handler;
//
//import com.example.ecommerce.domain.response.APIResponse;
//import com.example.ecommerce.handler.exception.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.multipart.MultipartException;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(value = GeneralException.class)
//    public APIResponse<?> handleGeneralException(GeneralException ex) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(value = ResourcesNotFoundException.class)
//    public APIResponse<?> handleAuthenticationException(ResourcesNotFoundException ex) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(value = AuthenticationFailureException.class)
//    public APIResponse<?> handleAuthenticationException(AuthenticationFailureException ex) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(value = CodeExpiredException.class)
//    public APIResponse<?> handleAuthenticationException(CodeExpiredException ex) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//
//    private APIResponse<?> apiResponseError(int status, String message, Object o) {
//        return new APIResponse<>(status, o, message);
//    }
//
//    @ExceptionHandler(value = MultipartException.class)
//    public APIResponse<?> handleParamFileException(MultipartException ex) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(value = UsernameNotFoundException.class)
//    public APIResponse<?> handleUsernameNotFoundException(
//            UsernameNotFoundException ex
//    ) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//    @ExceptionHandler(value = UserNameAlreadyExistsException.class)
//    public APIResponse<?> handleUsernameNotFoundException(
//            UserNameAlreadyExistsException ex
//    ) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public APIResponse<?> handleValidationErrors(
//            MethodArgumentNotValidException ex
//    ) {
//        return apiResponseError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null);
//    }
//}
