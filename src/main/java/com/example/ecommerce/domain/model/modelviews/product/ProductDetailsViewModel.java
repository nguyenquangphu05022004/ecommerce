package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.file.EntityType;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ProductDetailsViewModel extends ProductGalleryModelView {
    private List<Evaluation> evaluations;
    private Map<String, List<String>> attributeMaps;
    private List<String> imageUrls;
    public ProductDetailsViewModel(Product product) {
        super(product);
        this.evaluations = product.getEvaluations();
        this.attributeMaps = extractAttributeKey(product.getProductInventory());
        this.imageUrls = getImageUrl(EntityType.PRODUCT.name(), product.getImages());
    }

    private Map<String, List<String>> extractAttributeKey(
            Set<ProductInventory> productInventory) {
        Map<String, List<String>> entries = new HashMap<>();

        productInventory.stream().forEach(proInventory -> {
            String attributeCombinationKey = proInventory.getAttributeCombinationKey();
            var pairKeys = attributeCombinationKey.split(SystemUtils.SEPARATE);
            Arrays.stream(pairKeys).forEach(pair -> {
                String pairs[] = pair.split(":");
                if(entries.containsKey(pairs[0])) {
                    entries.get(pairs[0]).add(pairs[1]);
                } else {
                    entries.put(pairs[0], Arrays.asList(pairs[1]));
                }
            });
        });

        return entries;
    }
}