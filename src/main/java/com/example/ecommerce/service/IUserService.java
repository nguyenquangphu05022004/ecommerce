package com.example.ecommerce.service;

import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.APIResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService{
    void saveOrUpdate(RegisterRequest request);
    void uploadImage(MultipartFile multipartFile);
    APIResponse<?> getInfoUser();
}
