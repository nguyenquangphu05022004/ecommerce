package com.example.ecommerce.domain.model.binding;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@NoArgsConstructor
@Setter
public class ProductFilterRequest {
    @Getter
    @NotNull
    private Map<String, String> data;
    @Getter
    private String sortType;
    public Integer page;
    public Integer limit;

    public Integer getPage() {
        if(page == null) page = 1;
        return page;
    }
    public Integer getLimit() {
        if(limit == null) limit = 50;
        return limit;
    }
}
