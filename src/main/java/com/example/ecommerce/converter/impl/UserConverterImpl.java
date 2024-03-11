package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class UserConverterImpl implements IGenericConverter<User, UserDto> {
    private ModelMapper mapper;

    public UserConverterImpl(ModelMapper mapper) {
        this.mapper = mapper;
        skipProperty();
    }
    private void skipProperty() {
        TypeMap<User, UserDto> typeMap = this.mapper.createTypeMap(User.class, UserDto.class);
        typeMap.addMappings(map -> map.skip(UserDto::setBasket));
        typeMap.addMappings(map -> map.skip(UserDto::setPassword));

    }
    @Override
    public User toEntity(UserDto userDto) {
        User user =  mapper.map(userDto, User.class);
        user.setRole(Role.USER);
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto =  mapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public User toEntity(User user, UserDto userDto) {
        return null;
    }
}
