package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService extends IGenericService<UserDto>{
    UserDto saveOrUpdate(UserDto userDto);
    UserDto findUserByUsername(String username);
    List<UserDto> getListUserByRole(Role role);
    boolean changePassword(String oldPassword, String newPassword);
    boolean forgetPassword(String email);
    void updateAvatar(MultipartFile multipartFile);

}
