package com.example.ecommerce.frame.common.object;

import com.example.ecommerce.statistic.enums.FieldNameAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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

//    /**
//     *
//     * @param object: current object
//     * @param annotation: is marked on object field
//     * @param value: value want to set for field in object
//     */
//    public static void setField(Object object, String fieldSummary, Object value) {
////        Field[] fields = object.getClass().getDeclaredFields();
////        for(Field field : fields) {
////            A myAn = field.getAnnotation(annotation);
////            if(myAn != null) {
////                FieldNameAnnotation myAn1 = (FieldNameAnnotation) myAn;
////            }
////        }
//    }
}
