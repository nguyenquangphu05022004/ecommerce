package com.example.ecommerce.frame.common.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class PageParam {
    private static final Integer CURRENT_PAGE = 1;
    private static final Integer LIMIT_PER_PAGE = 10;


    @NotNull
    private Integer page = CURRENT_PAGE;
    @NotNull
    @Length(min = 10, max = 100, message = "Limit per page range [10, 100]")
    private Integer limit = LIMIT_PER_PAGE;
}
