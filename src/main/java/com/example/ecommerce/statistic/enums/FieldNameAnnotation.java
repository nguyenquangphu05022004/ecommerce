package com.example.ecommerce.statistic.enums;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldNameAnnotation {
    String name();
}
