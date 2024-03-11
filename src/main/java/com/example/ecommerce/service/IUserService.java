package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserDto;

public interface IUserService {
    UserDto saveOrUpdate(UserDto userDto);
    UserDto findUserByUsername(String username);

}
