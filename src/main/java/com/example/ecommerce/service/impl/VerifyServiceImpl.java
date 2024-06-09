package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.Verify;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VerifyRepository;
import com.example.ecommerce.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerifyServiceImpl implements VerifyService {
    private final VerifyRepository verifyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public VerifyServiceImpl(VerifyRepository verifyRepository,
                             UserRepository userRepository,
                             PasswordEncoder passwordEncoder) {
        this.verifyRepository = verifyRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean update(String code, String password) {
        Optional<Verify> optional = verifyRepository.findByCode(code);
        if(optional.isEmpty() || optional.get().isExpired()) {
            return false;
        }
        Verify verify = optional.get();
        User user = verify.getUser().toBuilder()
                .password(passwordEncoder.encode(password)).build();
        verify = verify.toBuilder()
                .user(user).build();
        verifyRepository.save(verify);
        return true;
    }


    @Override
    public boolean isCodeExpire(String code) {
        Optional<Verify> byCode = verifyRepository.findByCode(code);
        if(byCode.isPresent()) {
            return byCode.get().isExpired();
        }
        return false;
    }
}
