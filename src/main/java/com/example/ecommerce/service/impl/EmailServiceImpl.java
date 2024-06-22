package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.domain.Verify;
import com.example.ecommerce.domain.dto.EmailDetails;
import com.example.ecommerce.domain.dto.utilize.SendEmailTask;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VerifyRepository;
import com.example.ecommerce.service.EmailService;
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
    private final VerifyRepository verifyRepository;
    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(EmailDetails details) {
        try {
            Optional<User> userOptional = userRepository.findByEmail(details.getRecipient());
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
        Verify verify = user.getVerify();
        if(verify == null) {
            verify = Verify.builder()
                    .code(details.getCode())
                    .user(user)
                    .build();
        } else {
            verify = verify.toBuilder()
                    .code(details.getCode())
                    .build();
        }
        verifyRepository.save(verify);
    }
}