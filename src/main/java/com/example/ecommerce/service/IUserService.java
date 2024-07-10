package com.example.ecommerce.service;

import com.example.ecommerce.service.request.RegisterRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService{
    void saveOrUpdate(RegisterRequest request);
    void uploadImage(MultipartFile multipartFile);
}
