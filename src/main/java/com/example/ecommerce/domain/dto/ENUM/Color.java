package com.example.ecommerce.domain.dto.ENUM;

public enum Color {
    NO_COLOR("Không màu"),
    RED("Đỏ"), WHITE("Trắng"), BLUE("Xanh dương"),
    GREEN("Xanh lá"), YELLOW("Vàng"), PINK("Hồng"),
    VIOLET("Tím"), BLACK("Đen");
    private String value;

     Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
