package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.entities.order.states.item.LineItemFactory;
import com.example.ecommerce.domain.model.binding.LineItemRequest;
import com.example.ecommerce.handler.exception.ResourcesNotFoundException;
import com.example.ecommerce.repository.LineItemRepository;
import com.example.ecommerce.service.ILineItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LineItemServiceImpl implements ILineItemService {

    private final LineItemRepository lineItemRepository;

    @Override
    public void updateState(Long lineItemId, boolean isNext) {
        LineItem lineItem = lineItemRepository
                .findById(lineItemId)
                .orElseThrow(() -> {
                    return new ResourcesNotFoundException("Not found lineItem");
                });
        if(isNext) {
            LineItemFactory.getState(lineItem.getStateName())
                    .next(lineItem);
        } else {
            LineItemFactory.getState(lineItem.getStateName())
                    .prev(lineItem);
        }
    }
}
