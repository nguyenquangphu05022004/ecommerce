package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.ProductInventoryFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductInventoryRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.IProductInventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/inventories")
public class ProductInventoryController {
    private final IProductInventoryService productInventoryService;


    @PostMapping
    public APIResponse<ProductInventoryModelView> createProductInventory(
            @RequestPart("productInventoryRequest") @Valid ProductInventoryRequest request,
            @RequestParam("files") List<MultipartFile> files
    ) {
        request.setFiles(files);
        return productInventoryService.createProductInventory(request);
    }

    @PostMapping("/filter")
    public APIResponse<ProductInventoryModelView> getInventoryAttributeKey(
            @RequestBody ProductInventoryFilterRequest request
    ) {
        return productInventoryService.getProductInventory(request);
    }

}
