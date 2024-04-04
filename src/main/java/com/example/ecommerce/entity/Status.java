package com.example.ecommerce.entity;

public enum Status {
    SUCCESS("Thành công"),
    PROCESSING("Đang tiến hành"),
    NOT_ACCEPT("Chưa nhận đơn"),
    CANCEL("Đã hủy");
    private String name;
    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
