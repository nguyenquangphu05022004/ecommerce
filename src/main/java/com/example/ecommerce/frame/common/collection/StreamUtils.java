package com.example.ecommerce.frame.common.collection;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamUtils {

    public static <U> Boolean filter(Collection<U> t, Predicate<U> pre) {
        return t.stream().anyMatch(pre);
    }

    public static <U> Stream<U> filterAndThen(Collection<U> t, Predicate<U> pre) {
        return t.stream().filter(pre);
    }


    public static<U> IntStream mapInt(Collection<U> t, ToIntFunction<U> fuc) {
        if(CollUtils.isEmpty(t)) {
            return IntStream.of(0);
        }
        return t.stream().mapToInt(fuc);
    }

    public static<U> LongStream mapLong(Collection<U> t, ToLongFunction<U> fuc) {
        if(CollUtils.isEmpty(t)) {
            return LongStream.of(0);
        }
        return t.stream().mapToLong(fuc);
    }
    public static<U> DoubleStream mapDouble(Collection<U> t, ToDoubleFunction<U> fuc) {
        if(CollUtils.isEmpty(t)) {
            return DoubleStream.of(0);
        }
        return t.stream().mapToDouble(fuc);
    }
}
