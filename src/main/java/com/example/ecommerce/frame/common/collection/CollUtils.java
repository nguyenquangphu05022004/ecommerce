package com.example.ecommerce.frame.common.collection;

import com.example.ecommerce.product.dal.dataobject.comment.ProductCommentFavorite;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public static<U> boolean containsAll(Set<U> x1, Set<U> x2) {
        return x2.stream().allMatch(x -> {
            return x1.contains(x);
        });
    }
}
