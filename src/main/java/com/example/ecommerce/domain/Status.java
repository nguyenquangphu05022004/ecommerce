package com.example.ecommerce.domain;

public enum Status {
    ALL("Tất cả"),
    SUCCESS("Thành công"),
    PROCESSING("Đang tiến hành"),
    NOT_APPROVAL("Chưa nhận đơn");
    private String value;
    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
