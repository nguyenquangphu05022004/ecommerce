package com.example.ecommerce.frame.common.object;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ObjectUtils {


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
}
