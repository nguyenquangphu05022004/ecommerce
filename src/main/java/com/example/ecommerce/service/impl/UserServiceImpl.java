package com.example.ecommerce.service.impl;

import com.example.ecommerce.constant.Convert;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IGenericService;
import com.example.ecommerce.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements IGenericService<UserDto>, IUserService {
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
        return (UserDto) Convert.USER.toDto(userRepository.findByUsername(username));
    }

}
