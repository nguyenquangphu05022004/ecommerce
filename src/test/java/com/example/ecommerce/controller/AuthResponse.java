package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.model.binding.AuthenRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

public class AuthResponse {

    public static AuthenResponse authResponse(
            String apiVersion,
            MockMvc mockMvc,
            ObjectMapper objectMapper
    ) throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test@gmail.com");
        registerRequest.setFullName("nguyen van test");
        registerRequest.setPassword("test2004");
        registerRequest.setRole(Role.USER);
        registerRequest.setDateOfBirth(LocalDateTime.now());

        AuthenRequest authenRequest = new AuthenRequest();
        authenRequest.setPassword(registerRequest.getPassword());
        authenRequest.setUsername(registerRequest.getUsername());

        String json = objectMapper.writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder builder = httpRequestRegisterAccount(apiVersion, json);
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200));

        builder = htttpRequestLogin(apiVersion,objectMapper, authenRequest);
        String contentAsString = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName")
                        .value(registerRequest.getFullName()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        return objectMapper.readValue(contentAsString, new TypeReference<APIResponse<AuthenResponse>>() {}).getData();
    }

    private static MockHttpServletRequestBuilder htttpRequestLogin(String apiVersion,
                                                                   ObjectMapper objectMapper,
                                                                   AuthenRequest authenRequest) throws JsonProcessingException {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(authenRequest));
        return builder;
    }

    private static MockHttpServletRequestBuilder httpRequestRegisterAccount(
            String apiVersion, String json) {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/register"
                ).contentType("application/json")
                .content(json);
        return builder;
    }
}
