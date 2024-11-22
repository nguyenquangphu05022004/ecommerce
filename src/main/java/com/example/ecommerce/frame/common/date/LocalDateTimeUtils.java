package com.example.ecommerce.frame.common.date;

import java.time.LocalDateTime;

public class LocalDateTimeUtils {

    public static boolean isExpired(LocalDateTime localDateTime) {
        return localDateTime.isBefore(LocalDateTime.now());
    }
}
