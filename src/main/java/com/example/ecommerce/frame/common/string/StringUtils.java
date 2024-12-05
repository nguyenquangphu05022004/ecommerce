package com.example.ecommerce.frame.common.string;

import com.example.ecommerce.frame.common.collection.CollUtils;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringUtils {
    public static boolean compareIgnoreCase(String s1, String s2) {
        return s1.toLowerCase().compareTo(s2.toLowerCase()) == 0;
    }

    public static String getFromLastSubStr(String from, String originalStr) {
        return originalStr.substring(originalStr.lastIndexOf(from) + 1);
    }

    public static<T> String convertToString(Collection<T> coll, Function<T, String> func, String separate) {
        if(CollUtils.isEmpty(coll)) {
            return "";
        }
        return coll.stream().map(func).collect(Collectors.joining(separate));
    }
}
