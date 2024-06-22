package com.example.ecommerce.domain.dto;

import com.example.ecommerce.domain.Color;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockRequest {
    private Long id;
    private String code;
    private Long productId;
    private Color color;
    private String formatClassification; //(quantity,size);(quantity,size)
    private Integer price;
    private List<MultipartFile> multipartFiles;
}
