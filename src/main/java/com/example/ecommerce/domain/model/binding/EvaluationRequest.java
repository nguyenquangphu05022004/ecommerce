package com.example.ecommerce.domain.model.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class EvaluationRequest {
    @NotNull
    private Long productId;
    private Long evalParentId;
    @NotEmpty
    @Length(min = 6)
    private String content;
    @NotNull
    private Integer rating;
    private List<MultipartFile> files;

}
