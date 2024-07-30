package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.entities.auth.Token;
import com.example.ecommerce.domain.entities.auth.TokenType;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.handler.exception.AuthenticationFailureException;
import com.example.ecommerce.handler.exception.CodeExpiredException;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.EmailService;
import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.service.dto.EmailDetails;
import com.example.ecommerce.service.request.AuthenRequest;
import com.example.ecommerce.service.request.ForgetPasswordRequest;
import com.example.ecommerce.service.request.PasswordChangeRequest;
import com.example.ecommerce.service.request.RegisterRequest;
import com.example.ecommerce.service.response.AuthenResponse;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenServiceImpl implements IAuthenService {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final EmailService emailService;
    @Override
    public AuthenResponse authenticate(AuthenRequest request) {
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
        return AuthenResponse.builder()
                        .token(jwtToken)
                        .refreshToken(refreshToken)
                        .expiredAt(jwtService.extractExpiration(jwtToken).getTime())
                        .fullName(user.getFullName())
                        .build();
    }


    @Override
    public OperationResponse registerAccount(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(request.getRole())
                .fullName(request.getFullName())
                .build();

        if(request.getRole() == null) user.setRole(request.getRole());
        userRepository.save(user);
        return OperationResponse.builder()
                .message("You created account successfully")
                .success(true)
                .build();
    }

    @Override
    public OperationResponse forgetPassword(String username) {
        User user = userRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s not found", username)));
        Token token = Token.builder()
                .user(user)
                .tokenType(TokenType.RESET_PASSWORD)
                .revoked(false)
                .expired(false)
                .value(SystemUtils.code())
                .build();
        tokenRepository.save(token);
        emailService.sendSimpleMail(
                EmailDetails.builder()
                        .recipient(username)
                        .subject("Forget Password")
                        .code(token.getValue())
                        .build()
        );
        return OperationResponse.builder()
                .success(true)
                .message("We were sending code verify for you. Please check your email and enter here")
                .build();
    }

    @Override
    public OperationResponse forgetPasswordVerifyCode(String code) {
        verifyToken(code);
        return OperationResponse.builder()
                .message("Token was checked")
                .success(true)
                .build();
    }


    @Override
    public OperationResponse forgetPasswordGeneration(ForgetPasswordRequest request) {
        Token token = verifyToken(request.getCode());
        User user = token.getUser();
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);
        return OperationResponse.builder()
                .success(true)
                .message("You updated for your password")
                .build();
    }

    @Override
    public OperationResponse changePassword(PasswordChangeRequest request) {
        if(SecurityUtils.username() == null) {
            throw new GeneralException("You will not login");
        }
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.username()).get();
        if(this.encoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(this.encoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return OperationResponse.builder()
                    .success(true)
                    .message("You changed your password")
                    .build();
        }
        throw new GeneralException("Password not match, You are not change password");
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
    private Token verifyToken(String code) {
        Token token = tokenRepository.findByValue(code)
                .orElseThrow(() -> new GeneralException(String.format("Forget password verify code: %s not found", code)));
        if(token.getCreatedDate().plusMinutes(5l).isBefore(LocalDateTime.now())) {
            throw new CodeExpiredException(String.format("Code: %s expired", code));
        }
        return token;
    }
}