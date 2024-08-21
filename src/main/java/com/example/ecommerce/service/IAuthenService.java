package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.AuthenRequest;
import com.example.ecommerce.domain.model.binding.ForgetPasswordRequest;
import com.example.ecommerce.domain.model.binding.PasswordChangeRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.domain.response.OperationResponse;

public interface IAuthenService {
    APIResponse<AuthenResponse> authenticate(AuthenRequest request);
    APIResponse<?> registerAccount(RegisterRequest request);
    APIResponse<?> forgetPassword(String username);

    APIResponse<?> forgetPasswordVerifyCode(String code);


    APIResponse<?> forgetPasswordGeneration(ForgetPasswordRequest request);

    APIResponse<?> changePassword(PasswordChangeRequest request);
}
