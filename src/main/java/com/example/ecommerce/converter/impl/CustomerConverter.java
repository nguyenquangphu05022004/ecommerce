package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.CustomerDto;
import com.example.ecommerce.entity.Customer;

public class CustomerConverter implements IGenericConverter<Customer, CustomerDto> {
    @Override
    public Customer toEntity(CustomerDto customerDto) {
        return null;
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        return null;
    }

    @Override
    public Customer toEntity(Customer customer, CustomerDto customerDto) {
        return null;
    }
}
