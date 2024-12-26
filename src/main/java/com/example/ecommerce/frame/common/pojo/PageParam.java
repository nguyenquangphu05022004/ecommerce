package com.example.ecommerce.frame.common.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class PageParam {
    private static final Integer CURRENT_PAGE = 1;
    private static final Integer LIMIT_PER_PAGE = 10;


    @NotNull
    @Schema(description = "Trang hien tai", example = "1")
    private Integer page = CURRENT_PAGE;
    @NotNull
    @Schema(description = "So luong item trong 1 trang", example = "20")
    @Length(min = 10, max = 100, message = "Limit per page range [10, 100]")
    private Integer limit = LIMIT_PER_PAGE;


    public Pageable buildPageRequest() {
        return PageRequest.of(this.page - 1, this.limit);
    }
}
