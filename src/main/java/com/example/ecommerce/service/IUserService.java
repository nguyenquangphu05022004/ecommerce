package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.response.APIResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService{
    APIResponse<?> uploadImage(MultipartFile multipartFile);
    APIResponse<?> getInfoUser();
    void updateOnlineStatus(String username, boolean b);
}
