package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class StockRequest {
    private String code;
    private Long productId;
    private Integer quantity;
    private List<MultipartFile> multipartFiles;
}
