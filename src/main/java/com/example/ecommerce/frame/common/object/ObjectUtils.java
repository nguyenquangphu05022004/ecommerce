package com.example.ecommerce.frame.common.object;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class ObjectUtils {


    public static <T, U> U get(T t, Function<T, U> func) {
        if(t == null) {
            return null;
        }
        return func.apply(t);
    }
    public static <T> T init(Class<T> clazz)  {
        try {
            Constructor<T> constructor = clazz.getConstructor(new Class[]{});
            return constructor.newInstance();
        } catch (NoSuchMethodException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                throw new RuntimeException(ex);
            }
        }
//    public static <T> T init(Class<T> clazz, Object... parameters) {
//        Class[] classes = new Class[parameters.length];
//        for(int i = 0; i < parameters.length; i++) {
//            classes[i] = parameters[i].getClass();
//        }
//        Constructor<T> constructor = clazz.getConstructor(classes);
//        return constructor.newInstance(parameters);
//    }

    public static void setField(Object object, String field, Object value) {
        try {
            object.getClass().getDeclaredField(field).set(object, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Object getField(Object object, String field) {
        try {
            return object.getClass().getDeclaredField(field).get(object);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
