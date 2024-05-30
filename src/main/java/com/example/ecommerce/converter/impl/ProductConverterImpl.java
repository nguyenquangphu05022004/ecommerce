package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.utils.Convert;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductConverterImpl implements IGenericConverter<Product, ProductDto> {

    @Override
    public Product toEntity(ProductDto productDto) {
        return Product.builder()
                .language(new Language(productDto.getLanguage().getNameVn(), productDto.getLanguage().getNameEn()))
                .price(productDto.getPrice())
                .category(Category.builder().id(productDto.getCategory().getId()).build())
                .description(productDto.getDescription()).build();
    }

    @Override
    public ProductDto toDto(Product product) {
        Vendor vendor = product.getVendor().toBuilder().products(null).user(null).build();

        TrackProductSeller productSeller = product.getProductSeller() != null ?
                TrackProductSeller.builder().numberOfProductsSold(product.getProductSeller().getNumberOfProductsSold()).build() : null;

        List<StockDto> stockDtos = product.getStocks() != null ?
                product.getStocks().stream().map(e -> {
                    return StockDto.builder()
                            .code(e.getCode())
                            .quantity(e.getQuantity())
                            .id(e.getId())
                            .imageDtos(
                                    getImageDtoByStock(e)
                            ).build();
                }).collect(Collectors.toList()) : new ArrayList<>();

        List<EvaluationDto> evaluations = product.getEvaluations() != null ?
                product.getEvaluations().stream().map(e -> (EvaluationDto) Convert.EVAL.toDto(e)).collect(Collectors.toList()) : new ArrayList<>();

        LanguageDto language = LanguageDto.builder().nameEn(product.getLanguage().getNameEn()).nameVn(product.getLanguage().getNameVn()).build();

        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .language(language)
                .category((CategoryDto) Convert.CATE.toDto(product.getCategory()))
                .stockDtos(stockDtos)
                .evaluations(evaluations)
                .trackProductSeller(productSeller)
                .vendor((VendorDto) Convert.VEND.toDto(vendor))
                .build();
        return productDto;
    }

    public static List<ImageDto> getImageDtoByStock(Stock e) {
        return e.getImages().stream().map(image -> {
            return ImageDto.builder()
                    .name(image.getName())
                    .shortUrl(image.getShortUrl())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Product toEntity(Product product, ProductDto productDto) {
        return null;
    }

}
