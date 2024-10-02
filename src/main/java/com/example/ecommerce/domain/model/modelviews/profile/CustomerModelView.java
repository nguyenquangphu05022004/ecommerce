package com.example.ecommerce.domain.model.modelviews.profile;

import com.example.ecommerce.domain.entities.Customer;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Order;
import com.example.ecommerce.domain.model.modelviews.order.OrderModelView;
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
    private List<OrderModelView> orderModelViews;
    private String address;
    private String province;
    private String district;
    private String ward;

    public CustomerModelView(Customer customer) {
        super(customer);
        this.orderModelViews = map(customer.getOrders());
        this.address = customer.getAddress();
        this.province = customer.getProvince();
        this.district = customer.getDistrict();
        this.ward =customer.getWard();
    }

    private List<OrderModelView> map(Set<Order> orders) {
        if (CollectionUtils.isEmpty(orders)) return Collections.emptyList();
        return orders.stream()
                .map(order -> new OrderModelView(order))
                .toList();
    }
}
