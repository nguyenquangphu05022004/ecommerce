package com.example.ecommerce.service;

// Importing required classes

import com.example.ecommerce.domain.dto.EmailDetails;

// Interface
public interface EmailService {

    String sendSimpleMail(EmailDetails details);

}
