package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class CategoryRequest {
    private String name;
    private ImageDto thumbnail;
    private MultipartFile file;
}
