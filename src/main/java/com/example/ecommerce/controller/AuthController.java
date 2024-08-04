package com.example.ecommerce.controller;

import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.ForgetPasswordRequest;
import com.example.ecommerce.service.request.PasswordChangeRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
@CrossOrigin("*")
public class AuthController {

    private final IAuthenService authenService;

    @PostMapping({"/login", "/sign-in"})
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenRequest authenRequest
    ) {
        return ResponseEntity.ok(authenService.authenticate(authenRequest));
    }

    @PostMapping({"/register", "/sign-up"})
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        OperationResponse operationResponse = authenService.registerAccount(request);
        return ResponseEntity.ok(operationResponse);
    }

    @PostMapping("/forget-password")
    public OperationResponse forgetPassword(@RequestParam("username") String username) {
        return authenService.forgetPassword(username);
    }

    @PostMapping("/forget-password-verify-code")
    public OperationResponse forgetPasswordVerifyCode(@RequestParam("code") String code) {
        return authenService.forgetPasswordVerifyCode(code);
    }

    @PostMapping("/forget-password/generation")
    public OperationResponse forgetPasswordGeneration(
            @RequestBody ForgetPasswordRequest request
            ) {
        return authenService.forgetPasswordGeneration(request);
    }

    @PostMapping("/password-change")
    public OperationResponse changePassword(
            @RequestBody PasswordChangeRequest request
    ) {
        return authenService.changePassword(request);
    }


}
