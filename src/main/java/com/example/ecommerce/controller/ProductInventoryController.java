package com.example.ecommerce.controller;

import com.example.ecommerce.domain.model.binding.ProductInventoryFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductInventoryRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductInventoryModelView;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.service.IProductInventoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "${api.version}"+ "/products/inventories")
public class ProductInventoryController {
    private final IProductInventoryService productInventoryService;
    private final ObjectMapper objectMapper;

    @PostMapping
    public APIResponse<ProductInventoryModelView> createProductInventory(
            @RequestParam("productInventoryRequest") String request,
            @RequestParam("files") List<MultipartFile> files
    ) throws JsonProcessingException {
        ProductInventoryRequest productInventoryRequest = this.objectMapper.readValue(
                request,
                new TypeReference<ProductInventoryRequest>() {}
        );
        productInventoryRequest.setFiles(files);
        return productInventoryService.createProductInventory(productInventoryRequest);
    }

    @PostMapping("/filter")
    public APIResponse<ProductInventoryModelView> getInventoryAttributeKey(
            @RequestBody ProductInventoryFilterRequest request
    ) {
        return productInventoryService.getProductInventory(request);
    }

}
