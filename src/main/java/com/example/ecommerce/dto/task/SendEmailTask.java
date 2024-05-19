package com.example.ecommerce.dto.task;

import com.example.ecommerce.dto.EmailDetails;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Getter
@Setter
@AllArgsConstructor
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
            mimeMessageHelper.setText(emailDetails.getMsgBody(), true);
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
