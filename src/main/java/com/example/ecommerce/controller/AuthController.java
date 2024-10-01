package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.AuthenRequest;
import com.example.ecommerce.domain.model.binding.ForgetPasswordRequest;
import com.example.ecommerce.domain.model.binding.PasswordChangeRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.domain.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.TypeDescriptor;

@RequiredArgsConstructor
@RequestMapping("${api.version}" + "/auth")
@RestController
@CrossOrigin("*")
public class AuthController {
    private final IAuthenService authenService;

    @PostMapping(value = { "/login", "/sign-in"})
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenRequest authenRequest
    ) {
        return ResponseEntity.ok(authenService.authenticate(authenRequest));
    }

    @PostMapping({"/register", "/sign-up"})
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenService.registerAccount(request));
    }


    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestParam("username") String username) {
        return ResponseEntity.ok(authenService.forgetPassword(username));
    }

    @PostMapping("/forget-password-verify-code")
    public APIResponse<?> forgetPasswordVerifyCode(@RequestParam("code") String code) {
        return authenService.forgetPasswordVerifyCode(code);
    }

    @PostMapping("/forget-password/generation")
    public APIResponse<?> forgetPasswordGeneration(
            @RequestBody ForgetPasswordRequest request
            ) {
        return authenService.forgetPasswordGeneration(request);
    }

    @PostMapping("/password-change")
    public APIResponse<?> changePassword(
            @RequestBody PasswordChangeRequest request
    ) {
        return authenService.changePassword(request);
    }


}
