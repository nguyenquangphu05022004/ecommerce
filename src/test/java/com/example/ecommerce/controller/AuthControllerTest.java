package com.example.ecommerce.controller;

import com.example.ecommerce.domain.entities.Role;
import com.example.ecommerce.domain.entities.Token;
import com.example.ecommerce.domain.entities.TokenType;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.model.binding.AuthenRequest;
import com.example.ecommerce.domain.model.binding.ForgetPasswordRequest;
import com.example.ecommerce.domain.model.binding.PasswordChangeRequest;
import com.example.ecommerce.domain.model.binding.RegisterRequest;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Value("${api.version}")
    private String apiVersion;

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
    private AuthenRequest authenRequest;
    private AuthenResponse authenResponse;
    private RegisterRequest registerRequest;
    @BeforeEach
    void setUp() {
        /**
         * Init register account
         */
        this.registerRequest = new RegisterRequest();
        this.registerRequest.setUsername("test@gmail.com");
        this.registerRequest.setFullName("nguyen van test");
        this.registerRequest.setPassword("test2004");
        this.registerRequest.setRole(Role.USER);
        this.registerRequest.setDateOfBirth(LocalDateTime.now());

        /**
         * Init auth Request
         */
        this.authenRequest = new AuthenRequest();
        this.authenRequest.setPassword(this.registerRequest.getPassword());
        this.authenRequest.setUsername(this.registerRequest.getUsername());

    }


    @AfterEach
    @Transactional
    void destroy() {
        try {
            userService.delete(this.registerRequest.getUsername());
        } catch (Exception e) {

        }
    }


    @Test
    void authenticate() throws Exception {
        register_account_success();
        MockHttpServletRequestBuilder builder = htttpRequestLogin();
        String contentAsString = mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.fullName")
                        .value(this.registerRequest.getFullName()))
                .andReturn()
                .getResponse()
                .getContentAsString();
        this.authenResponse = objectMapper.readValue(contentAsString, new TypeReference<APIResponse<AuthenResponse>>() {}).getData();
    }

    @Test
    void test_password_not_match_authenticate() throws Exception {
        register_account_success();
        this.authenRequest.setPassword("quangphu");
        MockHttpServletRequestBuilder builder = htttpRequestLogin();
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Your account has username or password not matches"));
    }

    @Test
    void test_username_not_found_authenticate() throws Exception {
        MockHttpServletRequestBuilder builder = htttpRequestLogin();
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Username not found"));
    }

    private MockHttpServletRequestBuilder htttpRequestLogin() throws JsonProcessingException {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/login"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(this.authenRequest));
        return builder;
    }


    @Test
    void register_account_fail_when_username_exists() throws Exception {
        register_account_success();
        String json = objectMapper.writeValueAsString(this.registerRequest);
        MockHttpServletRequestBuilder builder = httpRequestRegisterAccount(json);
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Username exists"));
    }

    @Test
    void register_account_success() throws Exception {
        String json = objectMapper.writeValueAsString(this.registerRequest);
        MockHttpServletRequestBuilder builder = httpRequestRegisterAccount(json);
            mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200));
    }

    private  MockHttpServletRequestBuilder httpRequestRegisterAccount(String json) {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/register"
                ).contentType("application/json")
                .content(json);
        return builder;
    }

    @Test
    void test_forget_password_success() throws Exception {
        this.registerRequest.setUsername("quangphu2050@gmail.com");
        this.register_account_success();
        String username = this.registerRequest.getUsername();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                apiVersion + "/auth/forget-password?username=" + username
        );
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("We sent code for restore your password through your email, please check it"));
    }

    @Test
    void test_username_not_match_exception_forgetPassword() throws Exception {
        String username = this.registerRequest.getUsername();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                apiVersion + "/auth/forget-password?username=" + username
        );
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(400))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value(String.format("%s not found", username)));
    }

    @Test
    void forgetPasswordVerifyCode() throws Exception {
        this.test_forget_password_success();
        //request send message forget password

        User user = userRepository.findByUsernameIgnoreCase(this.registerRequest.getUsername()).get();
        Token token = tokenRepository
                .findByUserIdAndTokenType(user.getId(), TokenType.RESET_PASSWORD)
                .orElse(null);

        //request verify code
        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                apiVersion + "/auth/forget-password-verify-code?code=" + token.getValue()
        );
        mockMvc.perform(b1)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("verify code ok"));


    }

    @Test
    void create_new_password_when_verify_code_forget() throws Exception {
        this.forgetPasswordVerifyCode();
        String password = "garp";
        User user = userRepository.findByUsernameIgnoreCase(this.registerRequest.getUsername()).get();
        Token token = tokenRepository
                .findByUserIdAndTokenType(user.getId(), TokenType.RESET_PASSWORD)
                .orElse(null);
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setCode(token.getValue());
        request.setPassword(password);

        MockHttpServletRequestBuilder b1 = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/forget-password/generation"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));

        mockMvc.perform(b1)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                        .value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("your password was updated success"));

        user = userRepository.findByUsernameIgnoreCase(user.getUsername()).get();
        boolean passwordMatch = encoder.matches(password, user.getPassword());
        assertThat(passwordMatch).isTrue();

    }

    @Test
    void test_change_password_when_no_login() throws Exception {

        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setOldPassword(registerRequest.getPassword());
        request.setNewPassword("hachiman");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/password-change"
                ).contentType("application/json")
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("you no login"));
    }
    @Test
    void test_change_password_when_logged_and_password_not_match() throws Exception {
        this.authenticate();
        PasswordChangeRequest request = new PasswordChangeRequest();
        request.setOldPassword("mahiru1");
        request.setNewPassword("hachiman");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/password-change"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("Password not match, You can't change password"));
    }
    @Test
    void test_change_password_success() throws Exception {
        this.authenticate();
        String username = registerRequest.getUsername();
        PasswordChangeRequest request = new PasswordChangeRequest();

        request.setOldPassword(registerRequest.getPassword());
        request.setNewPassword("hachiman");
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                        apiVersion + "/auth/password-change"
                ).contentType("application/json")
                .header("Authorization", "Bearer " + authenResponse.getToken())
                .content(objectMapper.writeValueAsString(request));
        mockMvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                        .value("your password was updated"));
        User user = userRepository.findByUsernameIgnoreCase(username).get();

        boolean passwordMatch = encoder.matches(request.getNewPassword(), user.getPassword());

        assertThat(passwordMatch).isTrue();
    }

    public AuthenResponse getAuthenResponse() {
        return authenResponse;
    }
}
