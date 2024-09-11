package com.example.ecommerce.domain.model.modelviews.product;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.modelviews.evaluation.EvaluationDetailsModelView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class ProductDetailsViewModel extends ProductGalleryModelView {
    private List<EvaluationDetailsModelView> evaluations;
    private Map<String, List<String>> attributeMaps;
    private Map<Long, List<String>> inventoryUrlsImage;
    private VendorModelView vendor;
    public ProductDetailsViewModel(final Product product) {
        super(product);
        this.evaluations = mapToEvalDetails(product.getEvaluations());
        this.attributeMaps = extractAttributeKey(product.getProductInventories());
        this.inventoryUrlsImage = extractUrlImages(product);
        vendor = new VendorModelView(product.getVendor());
    }

    public  Map<Long, List<String>> extractUrlImages(Product product) {
        if(!CollectionUtils.isEmpty(product.getProductInventories())) {
            Map<Long, List<String>> inventoryUrls = new HashMap<>();
             product.getProductInventories().stream()
                    .forEach(s -> {
                        inventoryUrls.put(s.getId(), getImageUrl(s.getImages()));
                    });
             return inventoryUrls;
        }
        return null;
    }

    private List<EvaluationDetailsModelView> mapToEvalDetails(List<Evaluation> evaluations) {
        if(CollectionUtils.isEmpty(evaluations)) return Collections.emptyList();
        return evaluations.stream()
                .map(EvaluationDetailsModelView::new)
                .toList();
    }

    private Map<String, List<String>> extractAttributeKey(
            List<ProductInventory> productInventory) {
        if(CollectionUtils.isEmpty(productInventory)) {
            return Collections.emptyMap();
        }
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
