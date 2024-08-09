package com.example.ecommerce.controller;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.auth.*;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.ForgetPasswordRequest;
import com.example.ecommerce.service.request.PasswordChangeRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder encoder;
    @BeforeEach
    void setUp() {
        userService.saveOrUpdate(userRequest());
    }

    private RegisterRequest userRequest() {
        RegisterRequest r = new RegisterRequest();
        r.setUserType(UserType.CUSTOMER);
        r.setFullName("nguyen quang phu");
        r.setUsername("quangphu2050@gmail.com");
        r.setPassword("mahiru");
        r.setRole(Role.ADMIN);
        return r;
    }

    @AfterEach
    void destroy() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void authenticate() throws Exception {
        AuthenRequest request = new AuthenRequest();
        request.setUsername("quangphu2050@gmail.com");
        request.setPassword("mahiru");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fullName")
                        .value("nguyen quang phu"));
    }

    @Test
    void test_password_not_match_authenticate() throws Exception {
        AuthenRequest request = new AuthenRequest();
        request.setUsername("quangphu2050@gmail.com");
        request.setPassword("quangphu");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Your account has username or password not matches"));
    }

    @Test
    void test_username_not_found_authenticate() throws Exception {
        AuthenRequest request = new AuthenRequest();
        request.setUsername("quangphu2052@gmail.com");
        request.setPassword("mahiru");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Username not found"));
    }


    @Test
    void test_fail_register() throws Exception {
        var r = userRequest();
        String json = objectMapper.writeValueAsString(r);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/register"
                ).contentType("application/json")
                .content(json);
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusValue")
                        .value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(false));
    }

    @Test
    void test_success_register() throws Exception {
        var r = userRequest();
        r.setUsername("quangphu050204.re@gmail.com");
        String json = objectMapper.writeValueAsString(r);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/register"
                ).contentType("application/json")
                .content(json);
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusValue")
                        .value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(true));
    }

    @Test
    void test_not_exception_forgetPassword() throws Exception {
        String username = "quangphu2050@gmail.com";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                "/api/v1/auth/forget-password?username=" + username
        );
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(true));
    }

    @Test
    void test_username_not_match_exception_forgetPassword() throws Exception {
        String username = "quangphu2051@gmail.com";
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                "/api/v1/auth/forget-password?username=" + username
        );
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(String.format("%s not found", username)));
    }

    @Test
    void forgetPasswordVerifyCode() throws Exception {
        String username = "quangphu2050@gmail.com";
        //request send message forget password
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                "/api/v1/auth/forget-password?username=" + username
        );
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("We were sending code verify for you. Please check your email and enter here"));
        ;

        User user = userRepository.findByUsernameIgnoreCase(username).get();
        Token token = tokenRepository
                .findByUserIdAndTokenType(user.getId(), TokenType.RESET_PASSWORD)
                .orElse(null);

        //request verify code
        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                "/api/v1/auth/forget-password-verify-code?code=" + token.getValue()
        );
        mockMvc.perform(b1)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Token was checked"));


    }

    @Test
    void forgetPasswordGeneration() throws Exception {
        String username = "quangphu2050@gmail.com";
        String password = "garp";
        User user = userRepository.findByUsernameIgnoreCase(username).get();
        final Token token = Token.builder()
                .tokenType(TokenType.RESET_PASSWORD)
                .value(SystemUtils.code())
                .user(user)
                .build();
        tokenRepository.save(token);
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setCode(token.getValue());
        request.setPassword(password);

        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                        "/api/v1/auth/forget-password/generation"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(b1)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success")
                        .value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("You updated for your password"));

        user = userRepository.findByUsernameIgnoreCase(username).get();
        boolean passwordMatch = encoder.matches(password, user.getPassword());

        assertThat(passwordMatch).isTrue();

    }

    @Test
    void test_change_password_when_no_login() throws Exception {

        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setOldPassword("mahiru");
        request.setNewPassword("hachiman");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/password-change"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("You will not login"));
    }
    @Test
    void test_change_password_when_logged_and_password_not_match() throws Exception {

        //login
        AuthenResponse authenResponse = login();

        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setOldPassword("mahiru1");
        request.setNewPassword("hachiman");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/password-change"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Password not match, You are not change password"));
    }
    @Test
    void test_change_password_success() throws Exception {
        String username = userRequest().getUsername();
        PasswordChangeRequest request = new PasswordChangeRequest();

        request.setOldPassword(userRequest().getPassword());
        request.setNewPassword("hachiman");

        AuthenResponse authenResponse = login();


        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        "/api/v1/auth/password-change"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("You changed your password"));
        User user = userRepository.findByUsernameIgnoreCase(username).get();

        boolean passwordMatch = encoder.matches(request.getNewPassword(), user.getPassword());

        assertThat(passwordMatch).isTrue();

    }

    private AuthenResponse login() throws Exception {
        AuthenRequest login = new AuthenRequest();
        login.setUsername(userRequest().getUsername());
        login.setPassword(userRequest().getPassword());
        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                        "/api/v1/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(login));
        String requestResponse = mockMvc.perform(b1).andReturn().getResponse().getContentAsString();
        AuthenResponse authenResponse = objectMapper.readValue(requestResponse, AuthenResponse.class);
        return authenResponse;
    }
}
