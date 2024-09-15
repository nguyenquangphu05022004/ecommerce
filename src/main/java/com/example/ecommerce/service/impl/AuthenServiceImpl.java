package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.config.jwt.JwtService;
import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.auth.*;
import com.example.ecommerce.domain.model.binding.*;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.AuthenticationFailureException;
import com.example.ecommerce.handler.exception.CodeExpiredException;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.UserNameAlreadyExistsException;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IAuthenService;
import com.example.ecommerce.domain.response.AuthenResponse;
import com.example.ecommerce.domain.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.ecommerce.domain.entities.EntityType.Type.CUSTOMER;
import static com.example.ecommerce.domain.entities.EntityType.Type.USER;
import static com.example.ecommerce.service.event.Event.EventType.SEND_MAIL;
import static com.example.ecommerce.service.event.Event.getInstance;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
public class AuthenServiceImpl implements IAuthenService {

    private final TokenRepository tokenRepository;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;

    @Override
    @Transactional
    public APIResponse<?> authenticate(AuthenRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
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
        AuthenResponse response = AuthenResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .expiredAt(jwtService.extractExpiration(jwtToken).getTime())
                .fullName(user.getFullName())
                .entityType(user.getEntityType())
                .userId(user.getId())
                .build();
        return apiResponse("user login", response);
    }


    @Override
    @Transactional
    public APIResponse<?> registerAccount(RegisterRequest request) {
        Optional<User> optionalUser = userRepository.findByUsernameIgnoreCase(request.getUsername());
        if (optionalUser.isEmpty()) {
            Customer customer = Customer.builder()
                    .createdBy(request.getUsername())
                    .build();
            customerRepository.save(customer);
            User user = User.builder()
                    .username(request.getUsername())
                    .password(encoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .fullName(request.getFullName())
                    .entityType(new EntityType(CUSTOMER, customer.getId()))
                    .build();
            userRepository.save(user);
            return apiResponse("register account", null);
        }
        throw new UserNameAlreadyExistsException("Username exists");
    }

    @Override
    public APIResponse<?> forgetPassword(String username) {
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
        getInstance().postEvent(
                SEND_MAIL,
                EmailDetails.builder()
                        .recipient(username)
                        .subject("Forget Password")
                        .content(token.getValue())
                        .build()
        );
        return apiResponse("We sent code for restore your password through your email, please check it", null);
    }

    @Override
    public APIResponse<?> forgetPasswordVerifyCode(String code) {
        verifyToken(code);
        return apiResponse("verify code ok", null);
    }


    @Override
    public APIResponse<?> forgetPasswordGeneration(ForgetPasswordRequest request) {
        Token token = verifyToken(request.getCode());
        User user = token.getUser();
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);
        return apiResponse("your password was updated success", null);
    }

    @Override
    public APIResponse<?> changePassword(PasswordChangeRequest request) {
        if (SecurityUtils.getUsername() == null) {
            throw new GeneralException("you no login");
        }
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername()).get();
        if (this.encoder.matches(request.getOldPassword(), user.getPassword())) {
            user.setPassword(this.encoder.encode(request.getNewPassword()));
            userRepository.save(user);
            return apiResponse("your password was updated", null);
        }
        throw new GeneralException("Password not match, You can't change password");
    }

    private void revokeAllToken(Long userId) {
        List<Token> tokens = tokenRepository.findAllByUserId(userId);
        if (tokens != null && tokens.size() != 0) {
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
        if (token.isExpired() || token.isRevoked() || token.getCreatedDate().plusMinutes(5l).isBefore(LocalDateTime.now())) {
            throw new CodeExpiredException(String.format("Code: %s expired or is revoked", code));
        }
        token.setExpired(true);
        token.setRevoked(true);
        return token;
    }
}
