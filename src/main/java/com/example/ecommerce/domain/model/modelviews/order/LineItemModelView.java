package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.order.Item;
import com.example.ecommerce.domain.entities.order.LineItem;
import com.example.ecommerce.domain.model.modelviews.profile.VendorModelView;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class LineItemModelView {
    private VendorModelView vendor;
    private List<ItemViewModel> items;

    public LineItemModelView(LineItem lineItem) {
        this.vendor = new VendorModelView(lineItem.getVendor());
        this.items = mapLineItem(lineItem.getItems());
    }

    private List<ItemViewModel> mapLineItem(Set<Item> items) {
        if(CollectionUtils.isEmpty(items)) return Collections.emptyList();
        return items.stream()
                .map(item -> new ItemViewModel(item))
                .toList();
    }
}
