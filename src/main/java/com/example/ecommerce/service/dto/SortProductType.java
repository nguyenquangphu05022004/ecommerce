package com.example.ecommerce.service.dto;

public enum SortProductType {

    DEFAULT("Mặc định"),
    RATE_AVERAGE("Đánh giá cao"),
    NUMBER_OF_SELLER("Số lượng bán"),
    PRICE("Giá bán");

    private String value;
    SortProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
