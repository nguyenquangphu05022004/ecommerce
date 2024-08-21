package com.example.ecommerce.controller;

import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@CrossOrigin("*")

public class UserController {

    private final IUserService userService;


    @PostMapping("/avatar")
    public APIResponse<?> uploadUserAvatar(@RequestParam("file") MultipartFile file) {
        return userService.uploadImage(file);
    }

    @GetMapping("/info")
    public APIResponse<?> getInfoUser() {
        return userService.getInfoUser();
    }
}
