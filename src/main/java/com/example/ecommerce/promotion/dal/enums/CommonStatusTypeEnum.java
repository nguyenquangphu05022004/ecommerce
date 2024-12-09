package com.example.ecommerce.promotion.dal.enums;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CommonStatusTypeEnum {
    UNUSED("Chua su dung"),
    USED("Da su dung"),
    EXPIRE("Het han");

    @JsonAnyGetter
    public final String name;
}
