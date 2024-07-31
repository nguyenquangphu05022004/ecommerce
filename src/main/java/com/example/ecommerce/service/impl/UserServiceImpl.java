package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.CustomStatusCode;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.domain.model.dto.UserDto;
import com.example.ecommerce.domain.model.modelviews.profile.UserModelView;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.request.VendorRequest;
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
        User user = User.builder()
                .userType(UserType.CUSTOMER)
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .userType(request.getUserType())
                .build();
        if(request instanceof VendorRequest) {

        }
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

    @Override
    public void updateOnlineStatus(String username, boolean b) {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setOnline(b);
        userRepository.save(user);
    }


}
