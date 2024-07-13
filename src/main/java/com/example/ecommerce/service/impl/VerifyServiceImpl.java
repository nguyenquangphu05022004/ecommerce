package com.example.ecommerce.service.impl;

import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyServiceImpl implements VerifyService {
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean update(String code, String password) {
//        Optional<Verify> optional = verifyRepository.findByCode(code);
//        if(optional.isEmpty() || !optional.get().isExpired()) {
//            return false;
//        }
//        Verify verify = optional.get();
//        User user = verify.getUser().toBuilder()
//                .password(passwordEncoder.encode(password)).build();
//        verify = verify.toBuilder()
//                .user(user).build();
//        verifyRepository.save(verify);
        return true;
    }


    @Override
    public boolean isCodeExpire(String code) {
//        Optional<Verify> byCode = verifyRepository.findByCode(code);
//        if(byCode.isPresent()) {
//            return byCode.get().isExpired();
//        }
        return false;
    }
}
