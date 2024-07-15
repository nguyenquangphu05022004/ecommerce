package com.example.ecommerce.service.request;

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
        if(limit == null) limit = 15;
        return limit;
    }

    public Integer getPage() {
        if(page == null) page = 1;
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
