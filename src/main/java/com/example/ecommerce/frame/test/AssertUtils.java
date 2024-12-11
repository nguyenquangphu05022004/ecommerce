package com.example.ecommerce.frame.test;


import com.example.ecommerce.frame.common.exception.ErrorCode;
import com.example.ecommerce.frame.common.exception.ServiceException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AssertUtils {

    public static void assertPojoEquals(Object expected, Object actual, String... ignoreFields) {
        Field[] expectedFields = expected.getClass().getDeclaredFields();
        Arrays.stream(expectedFields).forEach(s -> {
            if(Arrays.stream(ignoreFields).anyMatch(i -> i.compareTo(s.getName()) == 0)) return;

            if(Collection.class.isAssignableFrom(s.getType())) return;

            try {
                Field actualField = actual.getClass().getDeclaredField(s.getName());
                Assertions.assertThat(getValue(actual, actualField)).isEqualTo(getValue(expected, s));
            } catch (NoSuchFieldException ex) {
                System.out.println("not field");
            }
        });
    }

//    public static  void assertServiceException(Executable executable, ErrorCode errorCode) {
//        ServiceException serviceException = Assertions.assertThrows(ServiceException.class, executable);
//
//        Assertions.assertEquals(errorCode.getCode(), serviceException.getCode(), "Error code not match");
//        Assertions.assertEquals(errorCode.getMessage(), serviceException.getMessage(), "Message not match");
//
//    }


    private static Object getValue(Object ob, Field field) {
        System.out.println("Field: " + field.getName());
        String method = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        try {
            Method declaredMethod = ob.getClass().getDeclaredMethod(method);
            return declaredMethod.invoke(ob);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    public static void assertException(ErrorCode errorCode, Executable executable) {
        ServiceException serviceException = org.junit.jupiter.api.Assertions.assertThrows(ServiceException.class, executable);
        assertPojoEquals(serviceException, errorCode);
    }
}
