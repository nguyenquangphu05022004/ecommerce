package com.example.ecommerce.domain.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class StockRequest {
    private String code;
    private Long productId;
    private Long decorationId;
    private Integer quantityOfProduct;
    private Integer price;
    private List<MultipartFile> multipartFiles;
}
