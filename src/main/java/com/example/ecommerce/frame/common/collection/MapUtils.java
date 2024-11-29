package com.example.ecommerce.frame.common.collection;

import com.example.ecommerce.frame.common.pojo.Pair;

import java.util.*;

public class MapUtils {
    public static <K, V>Map<K, Set<V>> convertToMapList(Set<Pair<K, V>> pairs) {
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
    public static <K, V>Map<K, List<V>> convertToMapList(List<Pair<K, V>> pairs) {
        Map<K, List<V>> map = new HashMap<>();
        if(CollUtils.isEmpty(pairs)) {
            return map;
        }
        for(Pair<K, V> pair : pairs) {
            if(map.containsKey(pair.getKey())) {
               map.get(pair.getKey()).add(pair.getValue());
            } else {
                map.put(pair.getKey(), new ArrayList<>(List.of(pair.getValue())));
            }
        }
        return map;
    }

    public static<K, V> boolean isEmpty(Map<K, V> map) {
        if(map.size() == 0) {
            return true;
        }
        return false;
    }
}
