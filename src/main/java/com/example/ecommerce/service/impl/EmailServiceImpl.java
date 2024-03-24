package com.example.ecommerce.service.impl;
import com.example.ecommerce.dao.EventDAO;
import com.example.ecommerce.dao.impl.UserEventDao;
import com.example.ecommerce.dto.EmailDetails;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.entity.Verify;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.repository.VerifyRepository;
import com.example.ecommerce.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

// Annotation
@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final UserRepository userRepository;
    private final VerifyRepository verifyRepository;
    private final UserEventDao userEventDao;
    @Value("${spring.mail.username}")
    private String sender;
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            UserRepository userRepository,
                            VerifyRepository verifyRepository,
                            UserEventDao userEventDao) {
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
        this.verifyRepository = verifyRepository;
        this.userEventDao = userEventDao;
    }

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details) {

        // Try block to check for exceptions
        try {
            Optional<User> userOptional = userRepository.findByEmail(details.getRecipient());
            if(userOptional.isEmpty()) return String.format("Email: %s.\n Không tồn tại trong hệ thống.", details.getRecipient());
            MimeMessage mailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailMessage, "utf-8");
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody(), true);
            mimeMessageHelper.setSubject(details.getSubject());
            saveDataVerify(details, userOptional.get());
            javaMailSender.send(mailMessage);
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
                    .status(true)
                    .user(user)
                    .build();
        } else {
            verify = verify.toBuilder()
                    .code(details.getCode())
                    .status(true).build();
        }
        verifyRepository.save(verify);
        userEventDao.createEvent(user);
    }

    // Method 2
    // To send an email with attachment
    public String
    sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}