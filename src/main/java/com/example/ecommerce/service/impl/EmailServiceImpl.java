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
    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailDetails details) {
        try {
            Thread emailTask = new Thread(new SendEmailTask(details, javaMailSender, sender));
            emailTask.start();
            return String.format("Chúng tôi đã gửi mã xác nhận vào email: %s.\n Vui lòng kiểm tra.\n Mã sẽ hết hạn trong 5 phút.", details.getRecipient());
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi trong quá trình gửi Email";
        }
    }
}
