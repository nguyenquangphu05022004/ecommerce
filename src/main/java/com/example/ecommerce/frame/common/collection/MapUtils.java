package com.example.ecommerce.frame.common.collection;

import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.lambda.Function;
import com.example.ecommerce.frame.common.pojo.Pair;

import java.util.*;

public class MapUtils {

    public static <K, V, M>Map<K, Set<V>> convertToMapSet(Set<M> coll,
                                                       java.util.function.Function<M, K> func1,
                                                       java.util.function.Function<M, V> func2
    ) {
        Map<K, Set<V>> map = new HashMap<>();
        if(CollUtils.isEmpty(coll)) {
            return map;
        }
        for(M m : coll) {
            K k = func1.apply(m);
            V v = func2.apply(m);
            if(map.containsKey(k)) {
                map.get(k).add(v);
            } else {
                map.put(k, new HashSet<>(Set.of(v)));
            }
        }
        return map;
    }
    public static <K, V>Map<K, Set<V>> convertToMapSet(Set<Pair<K, V>> pairs) {
        Map<K, Set<V>> map = new HashMap<>();
        if(CollUtils.isEmpty(pairs)) {
            return map;
        }
        for(Pair<K, V> pair : pairs) {
            if(map.containsKey(pair.getKey())) {
                map.get(pair.getKey()).add(pair.getValue());
            } else {
                map.put(pair.getKey(), new HashSet<>(Set.of(pair.getValue())));
            }
        }
        return map;
    }


    public static <K, V, S, N> Map<K, N> combinationTwoMap(Map<K, V> map1, Map<K, S> map2, Function<V, S, N> func) {
        Map<K, N> map = new HashMap<>();
        map1.entrySet().stream().forEach(entry -> {
            map.put(entry.getKey(), func.apply(entry.getValue(), map2.get(entry.getKey())));
        });
        return map;
    }


    public static <K, V, N> Map<K, N> convertMap(Map<K, V> map1,  Function<K, V, N> func) {
        Map<K, N> map = new HashMap<>();
        map1.entrySet().stream().forEach(entry -> {
            map.put(entry.getKey(), func.apply(entry.getKey(), entry.getValue()));
        });
        return map;
    }

    public static <K, V> Map<String, V> convertMap(Map<K, V> map1, java.util.function.Function<K, String> func) {
        Map<String, V> map = new HashMap<>();
        map1.entrySet().stream().forEach(entry -> {
            map.put(func.apply(entry.getKey()), entry.getValue());
        });
        return map;
    }


    public static <K, V> Map<K, Set<V>> convertListMap(Set<Map<K, Set<V>>> kvCollection) {
        Map<K, Set<V>> res = new HashMap<>();
        if(CollUtils.isEmpty(kvCollection)) return res;
        kvCollection.stream().forEach(map -> {
            if(res.isEmpty()) {
                res.putAll(map);
            } else {
                union(res, map);
            }
        });
        return res;
    }

    public static<K, V> void union(Map<K, Set<V>> want, Map<K, Set<V>> to) {
        if(isEmpty(want) && isEmpty(to)) return;
        to.entrySet().stream().forEach(entry -> {
            if(want.containsKey(entry.getKey())) {
                want.get(entry.getKey()).addAll(entry.getValue());
            }
        });
    }

//    public static <K, V, M> Map<K, V> convertToMap(Collection<M> coll,
//                                                  java.util.function.Function<M, K> func1,
//                                                  java.util.function.Function<M, V> func) {
//        Map<K, V> map = new LinkedHashMap<>();
//        if(CollUtils.isEmpty(coll)) {
//            return map;
//        }
//        for(var value : coll) {
//
//        }
//        return map;
//    }


    public static <K, V>Map<K, V> convertToMap(Collection<Pair<K, V>> pairs) {
        Map<K, V> map = new LinkedHashMap<>();
        if(CollUtils.isEmpty(pairs)) {
            return map;
        }
        for(Pair<K, V> pair : pairs) {
            map.put(pair.getKey(), pair.getValue());
        }
        return map;
    }

    public static<K, V> boolean isEmpty(Map<K, V> map) {
        if(map == null || map.size() == 0) {
            return true;
        }
        return false;
    }
}
