package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.CategoryDto;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class CategoryRequest extends CategoryDto {
    private MultipartFile file;
}
