package com.example.ecommerce.frame.common.collection;

import com.example.ecommerce.frame.common.pojo.Pair;

import java.util.*;

public class MapUtils {
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

    public static <K, V>Map<K, V> convertToMap(List<Pair<K, V>> pairs) {
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
