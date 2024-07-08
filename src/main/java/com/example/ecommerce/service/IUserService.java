package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.UserResponseInfo;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.service.dto.UserDto;
import com.example.ecommerce.service.request.RegisterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService{
    void saveOrUpdate(RegisterRequest request);
    void uploadImage(MultipartFile multipartFile);
}
