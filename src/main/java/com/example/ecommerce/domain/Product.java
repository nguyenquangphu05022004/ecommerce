package com.example.ecommerce.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class Product extends BaseEntity implements Observer<Notification> {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;
    @Column(columnDefinition = "text")
    private String description;
    @OneToMany(mappedBy = "product")
    private List<Evaluation> evaluations = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Stock> stocks = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @Override
    public void notification(Notification notification) {
        System.out.println(String.format(
                "NotifcationId %s has a message: %s",
                notification.getId(),
                notification.getMessage()));
        vendor.getVendorFavorite()
                .getUsers()
                .stream()
                .forEach(user -> user.getNotifications().add(notification));
    }
}



