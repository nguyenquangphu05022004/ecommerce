package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.converter.UserMapper;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.dto.ENUM.Role;
import com.example.ecommerce.domain.dto.chat.UserInboxResponse;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IImageService imageService;
    private final IFilesStorageService filesStorageService;
    private final ModelMapper mapper;

    @Override
    public List<UserResponseInfo> records() {
        return userRepository.findAll()
                .stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
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
    public UserResponseInfo findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("UserId", id + ""));
        return mapToDto(user);
    }

    @Override
    public UserResponseInfo saveOrUpdate(UserResponseInfo userResponseInfo) {
        User user = null;
        if (SecurityUtils.username() != null) {
            User oldUser = userRepository.findByUsername(SecurityUtils.username()).get();
        } else {
            user = null;
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return null;
    }

    @Override
    public UserResponseInfo findUserByUsername(String username) {
        return mapToDto(userRepository.findByUsername(username).get());
    }

    @Override
    public List<UserResponseInfo> getListUserByRole(Role role) {
        List<User> users = userRepository.findAllByRole(role);
        List<UserResponseInfo> userResponseInfos = users.stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
        return userResponseInfos;
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
                        SystemUtils.TAG);
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
    private UserResponseInfo mapToDto(User user) {
        user.getEvaluations().stream()
                .forEach(eval -> eval.setUser(null));
        return mapper.map(user, UserResponseInfo.class);

    }
}
