package com.example.ecommerce.domain;

import com.example.ecommerce.repository.NotificationRepository;
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
public class Product extends BaseEntity implements Observer{
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
    @Transient
    public void notify(NotificationRepository notificationRepository) {
        Notification notification = Notification.builder()
                .message(String.format("A product was created by shop %s, maybe you like that", this.vendor.getShopName()))
                .entityId(getId())
                .type(EntityType.PRODUCT)
                .build();
        notificationRepository.save(notification);
    }
}



