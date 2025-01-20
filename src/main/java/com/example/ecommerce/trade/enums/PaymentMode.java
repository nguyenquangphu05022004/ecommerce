package com.example.ecommerce.trade.enums;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum PaymentMode {
    BANK("Ngan hang"),
    RECEIPT("Khi nhan hang"),
    APP("Ung dung");
    private final String type;


    public static Map<String, String> getMap() {
        return MapUtils.convertToMap(CollUtils.convertList(Arrays.asList(PaymentMode.values()), p -> {
            return new Pair<>(p.name(), p.type);
        }));
    }
}
