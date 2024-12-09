package com.example.ecommerce.frame.common.collection;

import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamUtils {

    public static <U> Boolean filter(Collection<U> t, Predicate<U> pre) {
        return t.stream().anyMatch(pre);
    }
}
