package com.example.ecommerce.service;

import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.example.ecommerce.service.response.OperationResponse;

public interface IAuthenService {
    AuthenResponse authenticate(AuthenRequest request);
    OperationResponse registerAccount(RegisterRequest request);
}
