package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.CategoryDto;
import org.springframework.web.multipart.MultipartFile;

public class CategoryRequest extends CategoryDto {
    private MultipartFile file;
}
