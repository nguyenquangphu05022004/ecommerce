package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.model.modelviews.profile.UserModelView;
import com.example.ecommerce.domain.response.APIResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IUserService{
    APIResponse<UserModelView>  saveOrUpdate(RegisterRequest request);
    APIResponse<UserModelView>  uploadImage(MultipartFile multipartFile);
    APIResponse<UserModelView> getInfoUser();
    APIResponse<UserModelView>  updateOnlineStatus(String username, boolean b);
}
