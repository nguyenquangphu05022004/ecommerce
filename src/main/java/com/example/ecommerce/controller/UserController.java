package com.example.ecommerce.controller;

import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.request.RegisterRequest;
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

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        userService.saveOrUpdate(request);
        return ResponseEntity.ok("A user was created");
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> uploadUserAvatar(@RequestParam("file")MultipartFile file) {
        userService.uploadImage(file);
        return ResponseEntity.ok("Avatar of User was uploaded");
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfoUser() {
        return ResponseEntity.ok(userService.getInfoUser());
    }
}
