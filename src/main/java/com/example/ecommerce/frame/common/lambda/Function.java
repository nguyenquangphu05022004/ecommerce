package com.example.ecommerce.frame.common.lambda;

public interface Function<A, B, C> {
    C apply(A a, B b);
}
