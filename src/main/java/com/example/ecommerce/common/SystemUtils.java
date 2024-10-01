package com.example.ecommerce.common;

import java.util.Random;

public class SystemUtils {
    public static String code() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 12; i++) {
            int rd = new Random().nextInt(10);
            builder.append(rd);
        }
        return builder.toString();
    }
}
