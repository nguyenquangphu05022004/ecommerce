package com.example.ecommerce.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private Long id;
    private String name;
    private MultipartFile file;
}
