package com.example.ecommerce.frame.common.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {


    public static Date of(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = date.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }
}
