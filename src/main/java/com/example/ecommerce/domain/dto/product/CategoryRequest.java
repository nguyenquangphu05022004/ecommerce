package com.example.ecommerce.domain.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class CategoryRequest {
    private String name;
    private MultipartFile file;
}
