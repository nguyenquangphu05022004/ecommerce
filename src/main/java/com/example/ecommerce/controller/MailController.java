package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.EmailDetails;
import com.example.ecommerce.service.EmailService;
import com.example.ecommerce.utils.EmailUtils;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@Controller
public class MailController {
    private final EmailService emailService;

    @Autowired
    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email/forgot-password")
    public String forgotPasswordUser(@RequestParam("email") String email,
                                     Model model) {
        String code = SystemUtils.code();
        String url = MvcUriComponentsBuilder
                .fromMethodName(UserController.class, "getPageNewPassword", code)
                .build().toString();
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(email)
                .linkVerify(url)
                .subject(EmailUtils.FORGOT_PASSWORD)
                .code(code)
                .build();
        String message = emailService.sendSimpleMail(emailDetails);
        model.addAttribute("message", message);
        return "forget-password";
    }
}
