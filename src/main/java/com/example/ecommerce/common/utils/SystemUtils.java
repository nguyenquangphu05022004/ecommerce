package com.example.ecommerce.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SystemUtils {
    public static final Integer EVENT_TIME_HOUR_EXPIRE = 8;
    public static final Integer DEFAULT_PAGE = 1;
    public static final Integer LIMIT_ITEM = 9;
    public static final String TAG = "files/image";
    public static String code() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 12; i++) {
            int rd = new Random().nextInt(10);
            builder.append(rd);
        }
        return builder.toString();
    }
    public static String getFormatNumber(Integer value) {
        String number = String.valueOf(value);
        int n = number.length();
        StringBuilder builder = new StringBuilder();
        while(n > 0) {
            StringBuilder rev = new StringBuilder(number.substring(n - 3 >= 0 ? n - 3 : 0, n));
            builder.append(rev.reverse());
            if(n - 3 > 0) builder.append(",");
            n -= 3;
        }
        return builder.reverse().toString();
    }
    public static String getFormatDate(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
        return format.format(localDateTime);
    }



}
