package com.example.ecommerce.entity;

public enum Payment {
    PAY_AT_HOME("Thanh toán tại nhà"),
    PAT_BY_BANK("Thanh toán qua ngân hàng");
    private String name;
    Payment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
