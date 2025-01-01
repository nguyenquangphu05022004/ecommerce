package com.example.ecommerce.frame.common.array;

import com.example.ecommerce.frame.common.collection.CollUtils;

import java.lang.reflect.Array;
import java.util.Collection;

public class ArrayUtils {
    public static <T> T[] toArray(Class<T> clazz, Collection<T> coll) {
        T[] t = (T[]) Array.newInstance(clazz, coll.size());
        if(CollUtils.isEmpty(coll)) {
            return t;
        }
        int i = 0;
        for(T x : coll) {
            t[i ++] = x;
        }
        return t;
    }
}
