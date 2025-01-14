package com.example.ecommerce.frame.common.collection;

import org.springframework.util.CollectionUtils;

import java.util.*;
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


    public static  <U> Set<U> convertSet(Collection<Collection<U>> coll) {
        if(isEmpty(coll)) {
            return Collections.emptySet();
        }
        Set<U> set = new HashSet<>();
        coll.forEach(col -> {
            set.addAll(col);
        });
        return set;
    }

    public static  <U, T> Set<T> convertSet(Collection<U> coll, Function<U, T> func) {
        if(isEmpty(coll)) {
            return Collections.emptySet();
        }
//        return Collections.emptySet();
        return coll.stream().map(func).collect(Collectors.toSet());
    }


    public static <A, B,C> Set<C> convertSet(Map<A, B> map, com.example.ecommerce.frame.common.lambda.Function<A, B, Collection<C>> func) {
        if(MapUtils.isEmpty(map)) {
            return Collections.emptySet();
        }
//        return Collections.emptySet();
        Set<C> set = new HashSet<>();
        map.entrySet().forEach(entry -> {
            Collection<C> apply = func.apply(entry.getKey(), entry.getValue());
            set.addAll(apply);
        });
        return set;
    }


    public static Integer size(Collection collection) {
        if(isEmpty(collection)) {
            return 0;
        }
        return collection.size();
    }


    public static <T, S> S getFirst(Collection<T> objs, Function<T, S> func) {
        if(isEmpty(objs)) {
            return null;
        }
        return func.apply(objs.iterator().next());
    }

}
