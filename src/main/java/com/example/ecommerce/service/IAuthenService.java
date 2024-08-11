package com.example.ecommerce.service;

import com.example.ecommerce.domain.model.binding.AuthenRequest;
import com.example.ecommerce.domain.model.binding.ForgetPasswordRequest;
import com.example.ecommerce.domain.model.binding.PasswordChangeRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.domain.response.OperationResponse;

public interface IAuthenService {
    AuthenResponse authenticate(AuthenRequest request);
    OperationResponse registerAccount(RegisterRequest request);
    OperationResponse forgetPassword(String username);

    OperationResponse forgetPasswordVerifyCode(String code);


    OperationResponse forgetPasswordGeneration(ForgetPasswordRequest request);

    OperationResponse changePassword(PasswordChangeRequest request);
}
