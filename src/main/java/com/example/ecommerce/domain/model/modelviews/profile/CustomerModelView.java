package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.auth.Address;
import com.example.ecommerce.domain.entities.auth.Customer;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.order.Order;
import com.example.ecommerce.domain.model.modelviews.order.OrderViewModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Setter
public class CustomerModelView extends UserModelView {
    private List<OrderViewModel> orderViewModels;
    private Address address;

    public CustomerModelView(User user, Customer customer) {
        super(user);
        this.orderViewModels = map(customer.getOrders());
        this.address = customer.getAddress();
    }

    private List<OrderViewModel> map(Set<Order> orders) {
        if (CollectionUtils.isEmpty(orders)) return Collections.emptyList();
        return orders.stream()
                .map(order -> new OrderViewModel(order))
                .toList();
    }
}
