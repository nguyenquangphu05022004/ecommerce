package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.UserMapper;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.user.UserDto;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IImageService imageService;
    private final IFilesStorageService filesStorageService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           IImageService imageService,
                           IFilesStorageService filesStorageService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
        this.filesStorageService = filesStorageService;
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
        if (SecurityUtils.username() != null) {
            User oldUser = userRepository.findByUsername(SecurityUtils.username()).get();
            user = (User) Convert.USER.toEntity(oldUser, userDto);
        } else {
            user = (User) Convert.USER.toEntity(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return (UserDto) Convert.USER.toDto(userRepository.save(user));
    }

    @Override
    public UserDto findUserByUsername(String username) {
        return (UserDto) Convert.USER.toDto(userRepository.findByUsername(username).get());
    }

    @Override
    public List<UserDto> getListUserByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        List<UserDto> userDtos = users.stream()
                .map(e -> (UserDto) Convert.USER.toDto(e))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }


    @Override
    @Transactional
    public void updateAvatar(MultipartFile multipartFile) {
        try {
            User user = userRepository.findByUsername(SecurityUtils.username()).get();
            Image avatar = null;
            if (user.getAvatar() != null) {
                filesStorageService.deleteFile(user.getAvatar().getName());
                avatar = user.getAvatar().toBuilder()
                        .name(multipartFile.getOriginalFilename())
                        .build();
                filesStorageService.saveFile(multipartFile);
            }
            else {
                 avatar = imageService.uploadFile(
                         multipartFile,
                        SystemUtils.SHORT_URL_AVATAR);
            }
            user.setAvatar(avatar);
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isExistsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserInboxResponse findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return UserMapper.maptoResponse(user);
    }
}
