package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.LineItem;
import com.example.ecommerce.service.dto.LineItemDto;
import com.example.ecommerce.service.mapper.IMapper;
import org.springframework.stereotype.Service;

@Service("lineItemMapper")
public class LineItemMapper implements IMapper<LineItem, Object, LineItemDto> {
    @Override
    public LineItemDto toDto(LineItem lineItem) {
        return null;
    }

}
