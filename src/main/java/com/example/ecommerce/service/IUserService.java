package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.UserRequest;
import com.example.ecommerce.domain.dto.UserResponseInfo;
import com.example.ecommerce.domain.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService extends IGenericService<UserResponseInfo> {
    UserResponseInfo saveOrUpdate(UserRequest request);
    UserResponseInfo findUserByUsername(String username);
    List<UserResponseInfo> getListUserByRole(Role role);
    boolean changePassword(String oldPassword, String newPassword);
    void updateAvatar(MultipartFile multipartFile);
    boolean isExistsEmail(String email);
    UserInboxResponse findByUsername(String username);
}
