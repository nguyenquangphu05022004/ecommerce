package com.example.ecommerce.service;

import com.example.ecommerce.domain.response.UserInboxResponse;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.domain.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService extends IGenericService<UserDto>{
    UserDto saveOrUpdate(UserDto userDto);
    UserDto findUserByUsername(String username);
    List<UserDto> getListUserByRole(Role role);
    boolean changePassword(String oldPassword, String newPassword);
    void updateAvatar(MultipartFile multipartFile);
    boolean isExistsEmail(String email);

    UserInboxResponse findByUsername(String username);
}
