package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Image;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@ToString
@SuperBuilder(toBuilder = true)

@AllArgsConstructor
public class CategoryDto extends BaseDto{
    private String name;
    private Image image;
    private MultipartFile file;//store data thumbnail send from user
//    private List<ProductDto> products =new ArrayList<>();
}
