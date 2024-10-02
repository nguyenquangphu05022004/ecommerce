package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.Product;
import com.example.ecommerce.domain.entities.ProductAttributeMappingValue;
import com.example.ecommerce.domain.entities.ProductInventory;
import com.example.ecommerce.domain.model.binding.ProductInventoryFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductInventoryRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.ResourcesNotFoundException;
import com.example.ecommerce.repository.ProductInventoryRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IProductInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.example.ecommerce.domain.entities.EntityType.Type.PRODUCT;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@RequiredArgsConstructor
@Service
public class ProductInventoryServiceImpl implements IProductInventoryService {
    private final ProductInventoryRepository productInventoryRepository;
    private final IFilesStorageService filesStorageService;

    @Override
    public APIResponse<ProductInventoryModelView> createProductInventory(
            ProductInventoryRequest request
    ) {

        ProductInventory productInventory = ProductInventory.builder()
                .productAttributeMappingValues(
                        CollectionUtils.isEmpty(request.getAttrMapValueId())
                                ? null : request.getAttrMapValueId()
                                .stream()
                                .map(s -> ProductAttributeMappingValue
                                        .builder()
                                        .id(s)
                                        .build())
                                .collect(Collectors.toList())
                )
                .product(Product.builder()
                        .id(request.getProductId())
                        .build())
                .numberOfProductSold(0)
                .skuCode(request.getSkuCode())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
        productInventoryRepository.save(productInventory);

        try {
            if (productInventory.getImages() == null) productInventory.setImages(new ArrayList<>());

            productInventory.setImages(request.getFiles()
                    .stream()
                    .map(s -> filesStorageService.saveFile(s, new EntityType(PRODUCT, productInventory.getId())))
                    .toList());

        } catch (Exception e) {
            throw new GeneralException("file can't null");
        }
        return apiResponse("created product inventory", new ProductInventoryModelView(productInventory));
    }

    @Override
    public APIResponse<ProductInventoryModelView> getProductInventory(ProductInventoryFilterRequest request) {
//        ProductInventory inventory = productInventoryRepository
//                .findByProductIdAndAttribute(
//                        request.getProductId(),
//                        request.getAttrMapValueId()
//                                .stream()
//                                .mapToLong(s -> s)
//                                .sum()
//                )
//                .orElseThrow(() -> new GeneralException("product inventory not found"));
        return apiResponse("filter productInventory", new ProductInventoryModelView(null));
    }

    @Override
    public void delete(Long inventoryId) {
        ProductInventory inventory = productInventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourcesNotFoundException("Not found inventory with id: " + inventoryId));
        if (inventory.getImages() != null) {
            filesStorageService.deleteImage(inventory.getImages());
        }
        productInventoryRepository.delete(inventory);
    }
}
