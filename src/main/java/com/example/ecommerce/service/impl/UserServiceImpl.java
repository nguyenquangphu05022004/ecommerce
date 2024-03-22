package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> records() {
        return GenericService.records(userRepository, Convert.USER);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public UserDto saveOrUpdate(UserDto userDto) {
        User user = null;
        if(userDto.getId() != null) {
            User oldUser = GenericService.findById(userRepository, userDto.getId());
            user = (User) Convert.USER.toEntity(oldUser, userDto);
        } else {
            user = (User) Convert.USER.toEntity(userDto);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return (UserDto) Convert.USER.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return (UserDto) Convert.USER.toDto(userRepository.findByUsername(username).get());
    }

    @Override
    public List<UserDto> getListUserByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        List<UserDto> userDtos =  users.stream()
                .map(e -> (UserDto)Convert.USER.toDto(e))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public boolean forgetPassword(String email) {
        return false;
    }

}
