package com.example.ecommerce.frame.common.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.function.Function;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name = "Ket qua tra ve - CommonResult")
public class CommonResult<T> {
    @Schema(description = "Noi dung ve ket qua", example = "Lay danh sach san pham")
    private String message;
    @Schema(description = "Code tra ve", example = "200")
    private Integer code;
    @Schema(description = "Du lieu tra ve")
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

    public static <U, S> CommonResult<PageResult<S>> success(PageResult<U> pageResult, Function<U, S> func, Object...params) {
        PageResult<S> res = new PageResult<>();
        res.setCurrentPage(pageResult.getCurrentPage());
        res.setTotalPage(pageResult.getTotalPage());
        res.setList(pageResult.getList().stream().map(func).toList());
        return new CommonResult<>(null, 200, res);
    }

    public static <T> CommonResult<T> error(Integer code) {
        return new CommonResult<>(null, code, null);
    }
    public static <T> CommonResult<T> error(Integer code, String message) {
        return new CommonResult<>(message, code, null);
    }

}
