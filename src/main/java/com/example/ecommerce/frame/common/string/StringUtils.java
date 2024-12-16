package com.example.ecommerce.frame.common.string;

import com.example.ecommerce.frame.common.collection.CollUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StringUtils {
    public static boolean startWithAny(String str, String... any) {
        if(any.length == 0) return false;

        for(String prefix : any) {
            if(str.startsWith(prefix)) return true;
        }
        return false;
    }
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

    public static String title(String str) {
        String low = str.toLowerCase();
        return low.substring(0, 1).toUpperCase() + low.substring(1);
    }

    /**
     * Every word have first character is upper else lower
     * Hello Guy --- Ho Simulate Format
     * @return
     */
    public static String headerFormat(String str) {
         return Arrays.stream(str.split("\\s+")).map(s -> title(s))
                 .collect(Collectors.joining(" "));
    }
}
