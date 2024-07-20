package com.example.ecommerce.service.request;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.service.dto.SortProductType;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
public class FilterInputRequestProduct {
    private Map<String, String> mapKey = new HashMap<>();
    private Integer limit;
    private Integer page;
    private SortProductType sortProductType;
    public int getLimit() {
        if(limit == null) return SystemUtils.LIMIT_ITEM;
        return limit;
    }

    public Integer getPage() {
        if(page == null) return SystemUtils.DEFAULT_PAGE;
        return page;
    }

    public SortProductType getSortProductType() {
        if(this.sortProductType == null) return SortProductType.DEFAULT;
        return sortProductType;
    }

}
