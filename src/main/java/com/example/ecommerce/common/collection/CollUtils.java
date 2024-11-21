package com.example.ecommerce.common.collection;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollUtils {

    public static boolean isEmpty(Collection<?> coll) {
        if(CollectionUtils.isEmpty(coll)) {
            return true;
        }
        return false;
    }
    public static  <U, T>  List<T> convertList(Collection<U> coll, Function<U, T> func) {
        if(isEmpty(coll)) {
            return Collections.emptyList();
        }
        return coll.stream().map(func).collect(Collectors.toList());
    }
    public static  <U, T> Set<T> convertSet(Collection<U> coll, Function<U, T> func) {
        if(isEmpty(coll)) {
            return Collections.emptySet();
        }
        return coll.stream().map(func).collect(Collectors.toSet());
    }
}
