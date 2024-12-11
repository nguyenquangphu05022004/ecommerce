package com.example.ecommerce.trade.dal.dataobject.order;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@AllArgsConstructor
public enum OrderStatus {
    PENDING("Chua xu ly"),
    CANCEL("Da huy"),
    PROCESSING("Dang xu ly"),
    DELIVERED("Da giao hang");
    @Getter
    private final String value;
    public static Map<String, String> getOrderStatusMap() {
        return MapUtils.convertToMap(CollUtils.convertList(Arrays.asList(OrderStatus.values()), p -> {
            return new Pair<>(p.name(), p.value);
        }));
    }
}
