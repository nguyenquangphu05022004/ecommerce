package com.example.ecommerce.frame.common.date;

import java.time.LocalDateTime;

public class DateTimeUtils {

    public static boolean isExpired(LocalDateTime localDateTime) {
        return localDateTime.isBefore(LocalDateTime.now());
    }

}
