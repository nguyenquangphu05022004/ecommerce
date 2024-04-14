package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if(user.getVendor() != null) user.setVendor(null);
        if(user.getAvatar() != null) {
            user = user.toBuilder().avatar(user.getAvatar().toBuilder().user(null).build()).build();
        }
        if(user.getEvaluations() != null || user.getEvaluations().size() == 0) {
            List<Evaluation> evaluations = user.getEvaluations().stream().map(e -> {
                return e.toBuilder().user(null).build();
            }).collect(Collectors.toList());
            user.setEvaluations(evaluations);
        }
        UserDto userDto =  mapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public User toEntity(User user, UserDto userDto) {
        return user.toBuilder()
                .userContactDetails(userDto.getUserContactDetails())
                .email(userDto.getEmail())
                .build();
    }
}
