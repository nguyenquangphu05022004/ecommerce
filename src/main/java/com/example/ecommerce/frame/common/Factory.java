package com.example.ecommerce.frame.common;

import com.example.ecommerce.handler.exception.ResourcesNotFoundException;

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
            throw new ResourcesNotFoundException("Class: " + className + " not found");
        }
    }
}
