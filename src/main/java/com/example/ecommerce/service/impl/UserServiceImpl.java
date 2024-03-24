package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Verify;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.UserDto;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
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
        if (userDto.getId() != null) {
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
        List<UserDto> userDtos = users.stream()
                .map(e -> (UserDto) Convert.USER.toDto(e))
                .collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        if(passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }



    @Override
    public void updateAvatar(MultipartFile multipartFile) {
        try {

            User user = userRepository.findByUsername(SecurityUtils.username()).get();
            SystemUtils.FILES_STORAGE_SERVICE
                    .deleteFile(user.getAvatar().getName(),
                            SystemUtils.FOLDER_AVATAR);
            SystemUtils.FILES_STORAGE_SERVICE.saveFile(multipartFile, SystemUtils.FOLDER_AVATAR);
            Image avatar = Image.builder()
                    .name(multipartFile.getOriginalFilename())
                    .shortUrl(SystemUtils.SHORT_URL_AVATAR).build();
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
}
