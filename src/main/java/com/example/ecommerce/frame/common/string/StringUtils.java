package com.example.ecommerce.frame.common.string;

public class StringUtils {
    public static boolean compareIgnoreCase(String s1, String s2) {
        return s1.toLowerCase().compareTo(s2.toLowerCase()) == 0;
    }

    public static String getFromLastSubStr(String from, String originalStr) {
        return originalStr.substring(originalStr.lastIndexOf(from) + 1);
    }
}
