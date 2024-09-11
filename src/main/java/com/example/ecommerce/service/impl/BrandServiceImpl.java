package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.BrandRequest;
import com.example.ecommerce.domain.model.modelviews.product.BrandModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.repository.BrandRepository;
import com.example.ecommerce.service.IBrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements IBrandService {
    private final BrandRepository brandRepository;
    @Override
    public APIResponse<?> createBrand(BrandRequest proBrand) {
        ProductBrand brand = ProductBrand.builder()
                .name(proBrand.getName())
                .slug(proBrand.getSlug())
                .build();
        brandRepository.save(brand);
        return apiResponse("create brand", new BrandModelView(brand));
    }

    @Override
    public APIListResponse<?> getAllBrand(int page, int limit) {
        Page<ProductBrand> pages = brandRepository.findAll(PageRequest.of(page - 1, limit));
        return new APIListResponse<>(
                200,
                page,
                limit,
                pages.getTotalPages(),
                pages.getContent()
                        .stream()
                        .map(br -> new BrandModelView(br))
                        .toList()
        );

    }
}
