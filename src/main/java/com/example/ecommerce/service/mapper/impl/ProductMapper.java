package com.example.ecommerce.service.mapper.impl;

import com.example.ecommerce.domain.entities.Evaluation;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.service.dto.*;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("productMapper")
@RequiredArgsConstructor
public class ProductMapper implements IMapper<Product, ProductRequest, ProductDto> {
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> stockMapper;
    @Qualifier("evaluationMapper")
    private final IMapper<Evaluation, EvaluationRequest, EvaluationDto> evaluationMapper;
    @Qualifier("vendorMapper")
    private final IMapper<Vendor, VendorRequest, VendorDto> vendorMapper;
    @Qualifier("categoryMapper")
    private final IMapper<Category, CategoryRequest, CategoryDto> categoryMapper;

    @Override
    public Product toEntity(ProductRequest request) {
        Product product = Product.builder()
                .description(request.getDescription())
                .language(new Product.Language(request.getName(), request.getNameEn()))
                .category(Category.builder().id(request.getCategoryId()).build())
                .productBrand(request.getBrandId() != null ? ProductBrand.builder().id(request.getBrandId()).build() : null)
                .build();
        return product;
    }

    @Override
    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .name(product.getLanguage().getNameVn())
                .category(categoryMapper.toDto(product.getCategory()))
                .id(product.getId())
                .stocks(stockMapper.toDtoList(product.getStocks()))
                .evaluations(evaluationMapper.toDtoList(product.getEvaluations()))
                .vendor(vendorMapper.toDto(product.getVendor()))
                .brand(brandMapper(product.getProductBrand()))
                .build();
    }

    @Override
    public List<ProductDto> toDtoList(Collection<? extends Product> products) {
        List<ProductDto> results = new ArrayList<>();
        if(products != null) {
            products.forEach(product -> results.add(toDto(product)));
        }
        return results;
    }

    public static BrandDto brandMapper(ProductBrand productBrand) {
        if(productBrand == null) return null;
        return BrandDto.builder()
                .name(productBrand.getName())
                .id(productBrand.getId())
                .build();
    }
}
