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
import java.util.ArrayList;
import java.util.List;

public class AuthResponse {


    private static RegisterRequest getRegisterRequest() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("test@gmail.com");
        registerRequest.setFullName("nguyen van test");
        registerRequest.setPassword("test2004");
        registerRequest.setRole(Role.USER);
        registerRequest.setDateOfBirth(LocalDateTime.now());
        return registerRequest;
    }

    public static List<AuthenResponse> authResponse(
            String apiVersion,
            MockMvc mockMvc,
            ObjectMapper objectMapper,
            int numberOfAuthen
    ) throws Exception {
        List<RegisterRequest> registers=  new ArrayList<>();
        for(int i = 0; i < numberOfAuthen; i++) {
            RegisterRequest r = getRegisterRequest();
            r.setUsername(r.getUsername() + i);
            registers.add(r);
        }
        List<AuthenResponse> authenResponses = new ArrayList<>();
        registers.forEach(registerRequest -> {
            try {
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
                authenResponses.add(objectMapper.readValue(contentAsString, new TypeReference<APIResponse<AuthenResponse>>() {}).getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return authenResponses;
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
