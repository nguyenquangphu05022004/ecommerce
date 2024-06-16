package com.example.ecommerce.domain.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class EvaluationRequest {
    private Long productId;
    private int rating;
    private String content;
    private List<MultipartFile> files;
}
