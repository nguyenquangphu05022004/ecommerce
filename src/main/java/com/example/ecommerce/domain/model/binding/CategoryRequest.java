package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.common.InvalidMessage;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryRequest {
    private Long id;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 6)
    private String name;
    @NotEmpty(message = InvalidMessage.NOT_EMPTY)
    @Length(min = 6)
    private String slug;
    private MultipartFile file;
}
