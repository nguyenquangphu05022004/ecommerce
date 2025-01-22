package com.example.ecommerce.trade.enums;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    PENDING("Chờ xử lý", 25),
    CANCEL("Đã bị hủy", 0),
    PROCESSING("Đang xử lý", 50),
    SHIPPED("Đang vận chuyển", 75),
    DELIVERED("Đã giao hàng", 100);

    private final String value;
    private final Integer progress;
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
