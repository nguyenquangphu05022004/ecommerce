package com.example.ecommerce.frame.common.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static boolean isExpired(LocalDateTime localDateTime) {
        return localDateTime.isBefore(LocalDateTime.now());
    }
    public static boolean isBetween(LocalDateTime start, LocalDateTime end) {
        LocalDateTime now = LocalDateTime.now();
        return start.isBefore(now) && now.isBefore(end);
    }

    public static String format(LocalDateTime localDateTime) {
        if(localDateTime == null) {
            return null;
        }
        return localDateTime.format(FORMATTER);
    }

}
