package com.example.ecommerce.frame.common.pojo;


import lombok.Data;

import java.util.function.Function;

@Data
public class CommonResult<T> {
    private String message;
    private Integer code;
    private T data;

    private CommonResult(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }


    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(null, 200, data);
    }

    public static <U, S> CommonResult<S> success(U data, Function<U, S> func) {
        return new CommonResult<>(null, 200, func.apply(data));
    }

    public static <U, S> CommonResult<PageResult<S>> success(PageResult<U> pageResult, Function<U, S> func) {
        PageResult<S> res = new PageResult<>();
        res.setCurrentPage(pageResult.getCurrentPage());
        res.setTotalPage(pageResult.getTotalPage());
        res.setList(pageResult.getList().stream().map(func).toList());
        return new CommonResult<>(null, 200, res);
    }
}
