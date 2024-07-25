package com.example.ecommerce.controller;

import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.APIResponse;
import com.example.ecommerce.service.response.AuthenResponse;
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
        APIResponse<?> apiResponse = authenService.authenticate(authenRequest);
        return ResponseEntity.ok( apiResponse);
    }

    @PostMapping({"/register", "/sign-up"})
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        OperationResponse operationResponse = authenService.registerAccount(request);
        return ResponseEntity.ok(operationResponse);
    }



}
