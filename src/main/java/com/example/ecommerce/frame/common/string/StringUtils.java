package com.example.ecommerce.frame.common.string;

import com.example.ecommerce.frame.common.collection.CollUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringUtils {
    public static boolean startWithAny(String str, String... any) {
        if(any.length == 0) return false;

        for(String prefix : any) {
            if(str.startsWith(prefix)) return true;
        }
        return false;
    }
    public static boolean equalIgnoreCase(String s1, String s2) {
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

    /**
     * Format content
     * @param content: Hello {{name}}, welcome
     * @param map: {name: Quang Phu}
     * @return Hello Quang Phu, welcome
     */
    public static String formatContent(String content, Map<String, Object> map) {
        for(Map.Entry<String, Object> entry : map.entrySet()) {
            content = content.replace("{{" + entry.getKey() + "}}", entry.getValue().toString());
        }
        return content;
    }

    public static List<String> extractStr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        Set<String> placeholders = new HashSet<>();
        while (matcher.find()) {
            placeholders.add(matcher.group(1)); // Extract the content inside {}
        }
        return new ArrayList<>(placeholders);
    }


}
