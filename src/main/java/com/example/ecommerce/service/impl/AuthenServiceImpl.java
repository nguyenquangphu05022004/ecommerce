package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.CustomStatusCode;
import com.example.ecommerce.common.enums.TokenType;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.Token;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.handler.exception.AuthenticationFailureException;
import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.APIResponse;
import com.example.ecommerce.service.response.AuthenResponse;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements IAuthenService {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    public APIResponse<?> authenticate(AuthenRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if(!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthenticationFailureException("Your account has username or password not matches");
        }
        String jwtToken = jwtService.generateToken(request.getUsername());
        String refreshToken = jwtService.generateRefreshToken(request.getUsername());
        Token token = Token.builder()
                .tokenType(TokenType.BEARER)
                .value(jwtToken)
                .revoked(false)
                .expired(false)
                .user(user)
                .build();
        revokeAllToken(user.getId());
        tokenRepository.save(token);
        return new APIResponse<>(
                "OK",
                null,
                1,
                CustomStatusCode.SUCCESS.getNumber(),
                AuthenResponse.builder()
                        .token(jwtToken)
                        .refreshToken(refreshToken)
                        .expiredAt(jwtService.extractExpiration(jwtToken).getTime())
                        .build()
        );
    }


    @Override
    public OperationResponse registerAccount(RegisterRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getFullName(), "fullName");
        ValidationUtils.fieldCheckNullOrEmpty(request.getUsername(), "username");
        ValidationUtils.fieldCheckNullOrEmpty(request.getEmail(), "email");
        ValidationUtils.fieldCheckNullOrEmpty(request.getPassword(), "password");

        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .userContactDetails(UserContactDetails.builder().fullName(request.getFullName()).build())
                .build();

        if(request.getRole() == null) user.setRole(request.getRole());
        userRepository.save(user);
        return OperationResponse.builder()
                .message("You created account successfully")
                .success(true)
                .build();
    }
    private void revokeAllToken(Long userId) {
        List<Token> tokens = tokenRepository.findAllByUserId(userId);
        if(tokens != null && tokens.size() != 0) {
            tokens.forEach(token -> {
                token.setRevoked(true);
                token.setExpired(true);
                tokenRepository.save(token);
            });
        }
    }
}
