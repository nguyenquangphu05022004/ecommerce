package com.example.ecommerce.service.request;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.service.dto.SortProductType;
import lombok.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class FilterInputRequestProduct {
    private List<Pair<KeySearchRequest, String>> pairs;
    private Integer limit;
    private Integer page;
    private SortProductType sortProductType;
    public int getLimit() {
        if(limit == null) limit = SystemUtils.LIMIT_ITEM;
        return limit;
    }

    public Integer getPage() {
        if(page == null) page = SystemUtils.DEFAULT_PAGE;
        return page;
    }

    public SortProductType getSortProductType() {
        if(sortProductType == null) sortProductType = SortProductType.DEFAULT;
        return sortProductType;
    }

    @Data
    public static class Pair<K, V> {
        private K key;
        private V value;
    }
}
