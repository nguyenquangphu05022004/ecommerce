package com.example.ecommerce.trade.enums;

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
    SHIPPED("Dang van chuyen"),
    DELIVERED("Da giao hang");
    @Getter
    private final String value;
    public static Map<String, String> getOrderStatusMap() {
        return MapUtils.convertToMap(CollUtils.convertList(Arrays.asList(OrderStatus.values()), p -> {
            return new Pair<>(p.name(), p.value);
        }));
    }

    public static OrderStatus next(OrderStatus status) {
        return switch (status) {
            case PENDING -> PROCESSING;
            case SHIPPED -> DELIVERED;
            case CANCEL, DELIVERED -> null;
            case PROCESSING -> SHIPPED;
        };
    }


    public static OrderStatus prev(OrderStatus status) {
        return switch (status) {
            case PENDING, CANCEL -> null;
            case PROCESSING -> PENDING;
            case SHIPPED -> PROCESSING;
            case DELIVERED -> SHIPPED;
        };
    }

}
