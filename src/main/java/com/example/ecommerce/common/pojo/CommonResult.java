package com.example.ecommerce.common.pojo;


import lombok.Data;

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

}
