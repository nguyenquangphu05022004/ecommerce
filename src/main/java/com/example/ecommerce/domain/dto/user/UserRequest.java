package com.example.ecommerce.domain.dto.user;

import com.example.ecommerce.domain.UserContactDetails;
import com.example.ecommerce.domain.dto.product.ImageDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserRequest {
    private String username;
    private String password;
    private String email;
    private UserContactDetails userContactDetails;
}
