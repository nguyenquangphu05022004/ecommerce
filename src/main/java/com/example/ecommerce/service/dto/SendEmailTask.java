package com.example.ecommerce.service.dto;

import com.example.ecommerce.common.utils.EmailUtils;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
@NoArgsConstructor
public class SendEmailTask implements Runnable{
    private EmailDetails emailDetails;
    private JavaMailSender javaMailSender;
    private String emailSender;
    @Override
    public void run() {
        try {
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setFrom(emailSender);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setText(EmailUtils.bodyMessageForgotPassword(
                    emailDetails.getUsername(),
                    emailDetails.getLinkVerify()
            ), true);
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
