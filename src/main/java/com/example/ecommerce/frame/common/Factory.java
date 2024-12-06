package com.example.ecommerce.frame.common;

import com.example.ecommerce.frame.common.exception.ServiceException;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Factory {
    public static Object buildInstance(String classNameAddress,
                                Object[] objects)  {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(classNameAddress);
            Class<?>[] classes = new Class[objects.length];
            for(int i = 0; i < objects.length; i++) {
                classes[i] = objects[i].getClass();
            }
            Constructor<?> constructor = clazz.getConstructor(classes);
            return constructor.newInstance(objects);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
