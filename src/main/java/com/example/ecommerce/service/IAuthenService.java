package com.example.ecommerce.service;

import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.ForgetPasswordRequest;
import com.example.ecommerce.service.request.PasswordChangeRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.APIResponse;
import com.example.ecommerce.service.response.AuthenResponse;
import com.example.ecommerce.service.response.OperationResponse;

public interface IAuthenService {
    AuthenResponse authenticate(AuthenRequest request);
    OperationResponse registerAccount(RegisterRequest request);
    OperationResponse forgetPassword(String username);

    OperationResponse forgetPasswordVerifyCode(String code);


    OperationResponse forgetPasswordGeneration(ForgetPasswordRequest request);

    OperationResponse changePassword(PasswordChangeRequest request);
}
