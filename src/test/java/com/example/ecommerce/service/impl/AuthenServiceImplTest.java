package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.auth.Role;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.UserType;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.example.ecommerce.service.response.OperationResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class AuthenServiceImplTest {

    @Autowired
    private IAuthenService authenService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    void test_success_authenticate() {
        RegisterRequest r = new RegisterRequest();
        initAccount(r);

        AuthenRequest request = new AuthenRequest();
        request.setPassword("quangphu");
        request.setUsername("quangphu");
        AuthenResponse authenResponse = authenService.authenticate(request);

        assertThat(r.getFullName())
                .isEqualTo(authenResponse.getFullName());
    }

    @Test
    void test_fail_authenticate() {
        AuthenRequest request = new AuthenRequest();
        request.setPassword("hello");
        request.setUsername("quangphu");

        assertThatThrownBy(() -> {
                    authenService.authenticate(request);
                }).hasMessage("Username not found")
                .isInstanceOf(UsernameNotFoundException.class);

    }


    @Test
    void registerAccount() {
        RegisterRequest r = new RegisterRequest();
        initAccount(r);
        User user = userRepository.findByUsernameIgnoreCase("quangphu@gmail.com")
                .get();

        assertThat(r.getFullName())
                .isEqualTo(user.getFullName());

    }

    @Test
    @Disabled
    void forgetPassword() {
        RegisterRequest r = new RegisterRequest();
        initAccount(r);
        OperationResponse operationResponse = authenService.forgetPassword(r.getUsername());
        assertThat(operationResponse.getMessage())
                .isEqualTo("We were sending code verify for you. Please check your email and enter here");
    }

    @Test
    @Disabled
    void forgetPasswordVerifyCode() {
    }

    @Test
    @Disabled
    void forgetPasswordGeneration() {
    }

    @Test
    @Disabled
    void changePassword() {
    }



    void initAccount( RegisterRequest r) {
        r.setUsername("quangphu@gmail.com");
        r.setPassword("quangphu");
        r.setRole(Role.ADMIN);
        r.setFullName("nguyen quang phu");
        r.setUserType(UserType.CUSTOMER);
        userService.saveOrUpdate(r);
    }
}
