package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.auth.Token;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerifyServiceImpl implements VerifyService {
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean update(String code, String password) {
        Optional<Token> optional = tokenRepository.findByValue(code);
        if(optional.isEmpty() || !optional.get().isExpired()) {
            return false;
        }
        Token verify = optional.get();
        User user = verify.getUser().toBuilder()
                .password(passwordEncoder.encode(password)).build();
        verify = verify.toBuilder()
                .user(user).build();
        tokenRepository.save(verify);
        return true;
    }


    @Override
    public boolean isCodeExpire(String code) {
        Optional<Token> optionalToken = tokenRepository.findByValue(code);
        if(optionalToken.isPresent()) {
            return optionalToken.get().isExpired();
        }
        return false;
    }
}
