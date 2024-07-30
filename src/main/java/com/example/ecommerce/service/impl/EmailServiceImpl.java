package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.auth.TokenType;
import com.example.ecommerce.domain.entities.auth.Token;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.repository.TokenRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.EmailService;
import com.example.ecommerce.service.dto.EmailDetails;
import com.example.ecommerce.service.dto.SendEmailTask;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailDetails details) {
        try {
            Optional<User> userOptional = userRepository.findByUsernameIgnoreCase(details.getRecipient());
            if(userOptional.isEmpty()) return String.format("Email: %s.\n Không tồn tại trong hệ thống.", details.getRecipient());
            saveDataVerify(details, userOptional.get());
            details.setUsername(userOptional.get().getUsername());
            Thread emailTask = new Thread(new SendEmailTask(details, javaMailSender, sender));
            emailTask.start();
            return String.format("Chúng tôi đã gửi mã xác nhận vào email: %s.\n Vui lòng kiểm tra.\n Mã sẽ hết hạn trong 5 phút.", details.getRecipient());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi trong quá trình gửi Email";
        }
    }

    private void saveDataVerify(EmailDetails details, User user) {
        var optionalToken = tokenRepository.findByUserIdAndTokenType(
                user.getId(),
                TokenType.RESET_PASSWORD
        );
        Token token = null;
        if(optionalToken.isPresent()) {
            token = optionalToken.get()
                    .toBuilder()
                    .value(details.getCode())
                    .build();
        } else {
            token = Token.builder()
                    .value(details.getCode())
                    .user(user)
                    .build();
        }
        tokenRepository.save(token);
    }
}
