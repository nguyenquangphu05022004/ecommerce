package com.example.ecommerce.frame.operatelog.annotation;

import com.example.ecommerce.frame.operatelog.enums.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog{
    boolean logResults() default false;
    boolean logArgs() default false;
    OperationType operationType() default OperationType.OTHER;
}
