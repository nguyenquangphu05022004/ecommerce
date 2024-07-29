package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.service.dto.UserDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.RegisterRequest;
import org.springframework.stereotype.Service;

@Service("userMapper")
public class UserMapper implements IMapper<User, RegisterRequest, UserDto> {

    @Override
    public User toEntity(RegisterRequest request) {
        return IMapper.super.toEntity(request);
    }

    @Override
    public UserDto toDto(User user) {
        return null;
    }
}
