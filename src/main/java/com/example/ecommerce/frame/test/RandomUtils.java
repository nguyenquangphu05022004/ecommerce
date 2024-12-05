package com.example.ecommerce.frame.test;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class RandomUtils {
    private static EasyRandom random;

    static {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.stringLengthRange(10, 10);
        parameters.collectionSizeRange(5, 5);
        random = new EasyRandom(parameters);
    }

    public static String randomString() {
        return random.nextObject(String.class);
    }

    public static<T> List<T> randomList(Class<T> clazz) {
        return random.objects(clazz, 5).collect(Collectors.toList());
    }
    public static<T> List<T> randomList(Class<T> clazz, Consumer<T> comsumer) {
        List<T> list = random.objects(clazz, 5).toList();
        list.forEach(s -> comsumer.accept(s));
        return list;
    }
    public static<T> Set<T> randomSet(Class<T> clazz) {
        return random.objects(clazz, 5).collect(Collectors.toSet());
    }

    public static <T> T randomPojo(Class<T> clazz) {
        return random.nextObject(clazz);
    }
    public static <T> T randomPojo(Class<T> clazz, Consumer<T> ...consumers) {
        T t = randomPojo(clazz);
        if(consumers.length > 0) {
            Arrays.stream(consumers).forEach(consumer -> consumer.accept(t));
        }
        return t;
    }
}
