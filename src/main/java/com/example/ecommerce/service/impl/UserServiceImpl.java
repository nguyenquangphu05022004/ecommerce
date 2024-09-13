package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.model.modelviews.profile.UserModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.IVendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

import static com.example.ecommerce.domain.entities.EntityType.Type.USER;
import static com.example.ecommerce.domain.entities.EntityType.Type.VENDOR;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final IFilesStorageService filesStorageService;
    private final IVendorService vendorService;
    private final TokenRepository tokenRepository;
    private final CustomerRepository customerRepository;
    private final IEvaluationService evaluationService;
    @Override
    @Transactional
    public APIResponse<UserModelView>  uploadImage(MultipartFile multipartFile) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        try {
            if(user.getImages() == null) user.setImages(new ArrayList<>());

            user.getImages().add(filesStorageService.saveFile(
                    multipartFile,
                    new EntityType(USER, user.getId())
            ));

        } catch (Exception e) {
            throw new GeneralException("file can't null");
        }
        return apiResponse(user.getPassword() + " update avatar", null);
    }

    @Override
    public APIResponse<UserModelView>  getInfoUser() {
        User user = userRepository
                .findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return apiResponse("get info user",  new UserModelView(user));
    }

    @Override
    public APIResponse<UserModelView>  updateOnlineStatus(String username, boolean b) {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setOnline(b);
        userRepository.save(user);
        return apiResponse("change status user", null);
    }

    /**
     * Test
     * @param username
     */
    @Override
    @Transactional
    public void delete(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new GeneralException("not found user"));
        if(user.getEntityType().getEntityType() == VENDOR) {
            vendorService.delete(user.getEntityType().getEntityId());
        } else {
            customerRepository.deleteById(user.getEntityType().getEntityId());
        }
        if(user.getEvaluations() != null) {
            user.getEvaluations().forEach(s -> evaluationService.delete(s.getId()));
        }
        tokenRepository.deleteAllByUserId(user.getId());
        userRepository.delete(user);
    }


}
