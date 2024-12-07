package com.example.ecommerce.notification;

import com.example.ecommerce.frame.common.string.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService{
    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender javaMailSender;

    public void sendMail(String subject, String content, String recipient)   {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setText(content, true);
            mimeMessageHelper.setSubject(StringUtils.headerFormat(subject));
            javaMailSender.send(mailMessage);
        } catch (MessagingException ex) {
                throw new RuntimeException(ex);
            }
    }
}
