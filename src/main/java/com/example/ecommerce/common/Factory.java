package com.example.ecommerce.common;

import com.example.ecommerce.handler.exception.NotFoundException;

import java.lang.reflect.Constructor;

public class Factory {
    public static Object buildInstance(Class factoryInstance,
                                String className,
                                Class[] classes,
                                Object[] objects) {
        try {
            String name = factoryInstance.getPackageName() + "." + className;
            Class<?> clazz = Class.forName(name);
            Constructor<?> constructor = clazz.getConstructor(classes);
            return constructor.newInstance(objects);
        } catch (Exception e) {
            throw new NotFoundException("Class: " + className + " not found");
        }
    }
}
