package com.example.ecommerce.domain.model.modelviews.order;

import com.example.ecommerce.domain.entities.Item;
import com.example.ecommerce.domain.entities.LineItem;
import com.example.ecommerce.domain.model.modelviews.profile.VendorUserProfileModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class LineItemModelView {
    private VendorUserProfileModelView vendor;
    private List<ItemViewModel> items;

    public LineItemModelView(LineItem lineItem) {
        this.vendor = new VendorUserProfileModelView(lineItem.getVendor());
        this.items = mapLineItem(lineItem.getItems());
    }

    private List<ItemViewModel> mapLineItem(Set<Item> items) {
        if(CollectionUtils.isEmpty(items)) return Collections.emptyList();
        return items.stream()
                .map(item -> new ItemViewModel(item))
                .toList();
    }
}
