package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
@Data
@SuperBuilder(toBuilder = true)
public class CategoryRequest extends CategoryDto {
    private MultipartFile file;
}
