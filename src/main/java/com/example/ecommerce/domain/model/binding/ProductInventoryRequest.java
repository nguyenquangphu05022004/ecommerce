package com.example.ecommerce.domain.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductInventoryRequest
{
    private Set<Long> attrMapValueId;
    private Integer quantity;
    private Long productId;
    private String skuCode;
    private int price;
    private List<MultipartFile> files;
}
