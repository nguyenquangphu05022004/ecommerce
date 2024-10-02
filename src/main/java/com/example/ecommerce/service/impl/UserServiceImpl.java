package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Vendor;
import com.example.ecommerce.domain.model.modelviews.profile.UserModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IEvaluationService;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.IVendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    @Override
    @Transactional
    public APIResponse<UserModelView> uploadImage(MultipartFile multipartFile) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        try {
            if (user.getImages() == null) user.setImages(new ArrayList<>());

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
    public APIResponse<UserModelView> getInfoUser() {
        User user = userRepository
                .findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return apiResponse("get info user", new UserModelView(user));
    }

    @Override
    public APIResponse<UserModelView> updateOnlineStatus(String username, boolean b) {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setOnline(b);
        userRepository.save(user);
        return apiResponse("change status user", null);
    }

    /**
     * Start
     *Follow Action
     */
    @Override
    public APIResponse<?> followVendor(Long vendorId) {
        followAction(true, vendorId);
        return apiResponse("cancel follow vendor", null);
    }

    @Override
    public APIResponse<?> removeFollowVendor(Long vendorId) {
        followAction(false,vendorId);
        return apiResponse("cancel follow vendor", null);
    }

    private void followAction(boolean isFollow, Long vendorId) {
        User user = userRepository
                .findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new GeneralException("you aren't login"));

        Vendor vendor = (Vendor) userRepository
                .findById(vendorId)
                .orElseThrow(() -> new GeneralException(String.format("Vendor with id: %s not found", vendorId)));

        if(isFollow) {
            vendor.getUsersFavorite().add(user);
        } else {
            vendor.getUsersFavorite().remove(user);
        }
        userRepository.save(vendor);
    }

}
