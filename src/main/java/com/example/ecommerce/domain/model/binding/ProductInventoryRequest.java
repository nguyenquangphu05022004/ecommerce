package com.example.ecommerce.domain.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ProductInventoryRequest
{
    private Map<String, String> attributes;
    private Integer quantity;
    private Long productId;
    private String skuCode;
    private int price;
    private List<MultipartFile> files;
}
