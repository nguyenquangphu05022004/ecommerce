package com.example.ecommerce.service.event.listener;

import com.example.ecommerce.domain.model.binding.EmailDetails;
import com.example.ecommerce.service.event.Observer;
import com.example.ecommerce.handler.exception.GeneralException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String emailSender;

    public Observer<EmailDetails> sendMail() {
        return emailDetails -> {
            new Thread(() -> {
                try {
                    MimeMessage mailMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
                    mimeMessageHelper.setFrom(emailSender);
                    mimeMessageHelper.setTo(emailDetails.getRecipient());
                    mimeMessageHelper.setText(emailDetails.getContent(), true);
                    mimeMessageHelper.setSubject(emailDetails.getSubject());
                    javaMailSender.send(mailMessage);
                } catch (Exception e) {
                    throw new GeneralException("Email invalid");
                }
            }).start();

        };
    }
}
