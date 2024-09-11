package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.ProductInventoryFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductInventoryRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.ProductInventoryRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IProductInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@RequiredArgsConstructor
@Service
public class ProductInventoryServiceImpl implements IProductInventoryService {
    private final ProductInventoryRepository productInventoryRepository;
    private final IFilesStorageService filesStorageService;
    @Override
    public APIResponse<ProductInventoryModelView> createProductInventory(ProductInventoryRequest request) {
        String attr = null;
        if(!CollectionUtils.isEmpty(request.getAttributes())) {
            attr = request.getAttributes().entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue())
                    .collect(Collectors.joining(SystemUtils.SEPARATE));
        }
        ProductInventory productInventory = ProductInventory.builder()
                .product(Product.builder().id(request.getProductId()).build())
                .numberOfProductSold(0)
                .skuCode(request.getSkuCode())
                .quantity(request.getQuantity())
                .attributeCombinationKey(attr)
                .build();
        productInventoryRepository.save(productInventory);
        filesStorageService.saveFile(request.getImageRepresent(), productInventory.getId(), FileEntityType.PRODUCT_INVENTORY);
        return apiResponse("created product inventory", null);
    }

    @Override
    public APIResponse<ProductInventoryModelView> getProductInventory(ProductInventoryFilterRequest request) {
        ProductInventory inventory = productInventoryRepository
                .findByProductIdAndAttributeCombinationKey(
                        request.getProductId(),
                        request.getAttributeCombinationKey())
                .orElseThrow(() -> new GeneralException(
                        String.format("Key %s of product %s not found",
                                request.getAttributeCombinationKey(),
                                request.getProductId())
                ));
        return apiResponse("filter productInventory", new ProductInventoryModelView(inventory));
    }
}
