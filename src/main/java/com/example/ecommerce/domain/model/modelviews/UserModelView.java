package com.example.ecommerce.domain.model.modelviews;


import com.example.ecommerce.domain.*;
import com.example.ecommerce.service.mapper.ImageMapper;
import lombok.Getter;

import java.util.List;

@Getter
public class UserModelView extends ImageMapper {
    private long id;
    private String avatar;
    private List<EvaluationModelView> evaluations;
    private List<OrderViewModel> orders;
    private List<VendorModelView> vendors;
    private UserContactDetails userContactDetails;
    public UserModelView(User user) {
        this.id = user.getId();
        this.avatar = getImageUrl(EntityType.USER.name(), user.getUserImage());
        this.userContactDetails = user.getUserContactDetails();
        this.evaluations = user.getEvaluations() != null ? user.getEvaluations().stream().map(e -> new EvaluationModelView(e)).toList() : null;
        this.vendors = user.getVendors() != null ? user.getVendors().stream().map(e -> new VendorModelView(e)).toList() : null;
        this.orders = user.getOrders() != null ? user.getOrders().stream().map(e -> new OrderViewModel(e)).toList() : null;
    }
    @Getter
    public static class EvaluationModelView{
        private String content;
        private int rating;
        private long id;
        private ProductModelView product;
        protected EvaluationModelView(Evaluation e) {
            this.content = e.getContent();
            this.rating = e.getRating();
            this.id = e.getId();
            this.product = new ProductModelView(e.getProduct());
        }
    }
    @Getter
    public static class ProductModelView {
        private Long id;
        private String name;
        protected ProductModelView(Product p) {
            this.id = p.getId();
            this.name = p.getLanguage().getNameVn();
        }
    }
    @Getter
    public static class VendorModelView {
        private Long id;
        private String shopName;
        public VendorModelView(Vendor v) {
            this.id = v.getId();
            this.shopName = v.getShopName();
        }
    }

}
