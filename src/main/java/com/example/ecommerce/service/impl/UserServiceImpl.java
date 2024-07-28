package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.CustomStatusCode;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.model.modelviews.UserModelView;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.dto.UserDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Qualifier("userMapper")
    private final IMapper<User, RegisterRequest, UserDto> mapper;
    private final IFilesStorageService filesStorageService;

    @Override
    public void saveOrUpdate(RegisterRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getEmail(), "email");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUsername(), "username");
        ValidationUtils.fieldCheckNullOrEmpty(request.getPassword(), "password");
        ValidationUtils.fieldCheckNullOrEmpty(request.getFullName(), "fullName");
        User user = mapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void uploadImage(MultipartFile multipartFile) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.username()).get();
        filesStorageService.deleteImage(user.getUserImage());
        filesStorageService.saveFile(multipartFile, user.getId(), EntityType.USER);
    }

    @Override
    public APIResponse<?> getInfoUser() {
        User user = userRepository
                .findByUsernameIgnoreCase(SecurityUtils.username())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return new APIResponse<>(
                "ok",
                null,
                1,
                CustomStatusCode.SUCCESS.getNumber(),
                new UserModelView(user)
        );
    }


}
