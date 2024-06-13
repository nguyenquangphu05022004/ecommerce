package com.example.ecommerce.converter;

import com.example.ecommerce.domain.dto.product.BillDto;
import com.example.ecommerce.domain.Bill;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BillConverterImpl  {
    private final OrderConverter orderConverter;
    public BillDto toDto(Bill bill) {
        BillDto billDto = BillDto.builder()
                .id(bill.getId())
                .name(bill.getName())
                .order(orderConverter.toDto(bill.getOrder()))
                .status(bill.getStatus())
                .build();
        return billDto;
    }
}
