package com.example.ecommerce.domain.model.binding;

import com.example.ecommerce.service.algorithm.search.ProductFilterType;
import com.example.ecommerce.service.algorithm.sort.ProductSortType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@NoArgsConstructor
@Setter
public class FilterRequest {
    @Getter
    @NotNull
    private Map<ProductFilterType, String> data;
    @Getter
    @NotNull
    private ProductSortType sortType;
    public Integer page;
    public Integer limit;

    public Integer getPage() {
        if(page == null) page = 1;
        return page;
    }
    public Integer getLimit() {
        if(limit == null) limit = 20;
        return limit;
    }
}
