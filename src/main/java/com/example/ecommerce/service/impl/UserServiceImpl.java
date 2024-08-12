package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Customer;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.file.FileEntityType;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.model.binding.VendorRequest;
import com.example.ecommerce.domain.model.modelviews.profile.UserModelView;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.domain.response.APIResponse;
import lombok.RequiredArgsConstructor;
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
    private final IFilesStorageService filesStorageService;
    private final VendorRepository vendorRepository;
    private final CustomerRepository customerRepository;
    @Override
    public void saveOrUpdate(RegisterRequest request) {
        User user = User.builder()
                .userType(UserType.CUSTOMER)
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        if(request instanceof VendorRequest) {
            VendorRequest vendorRequest = (VendorRequest)request;
            Vendor vendor = Vendor.builder()
                    .shopName(vendorRequest.getShopName())
                    .perMoneyDelivery(vendorRequest.getPerMoneyDelivery())
                    .build();
            vendorRepository.save(vendor);
            user.setUserType(UserType.VENDOR);
            user.setUserTypeId(vendor.getId());
        } else {
            user.setUserTypeId(customerRepository.save(new Customer()).getId());
        }
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void uploadImage(MultipartFile multipartFile) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        filesStorageService.deleteImage(user.getUserImage());
        filesStorageService.saveFile(multipartFile, user.getId(), FileEntityType.USER);
    }

    @Override
    public APIResponse<?> getInfoUser() {
        User user = userRepository
                .findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return new APIResponse<>(
                "ok",
                null,
                1,
                200,
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
