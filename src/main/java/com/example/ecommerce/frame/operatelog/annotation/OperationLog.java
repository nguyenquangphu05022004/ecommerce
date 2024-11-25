package com.example.ecommerce.frame.operatelog.annotation;

import com.example.ecommerce.frame.operatelog.dto.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog{
    boolean enable() default true;
    boolean logResults() default false;
    boolean logArgs() default true;
    OperationType operationType() default OperationType.OTHER;
}
