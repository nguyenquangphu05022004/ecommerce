package com.example.ecommerce.converter;

import com.example.ecommerce.domain.Category;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.dto.product.ImageDto;
import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.domain.dto.product.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class ProductConverterImpl{

    private final ModelMapper mapper;
    public Product toEntity(ProductRequest productRequest) {
        Product product = mapper.map(productRequest, Product.class).toBuilder()
                .category(Category.builder()
                        .id(productRequest.getCategoryId())
                        .build())
                .build();
        return product;
    }

    public ProductDto toDto(Product product) {
//        TrackProductSeller productSeller = product.getProductSeller() != null ?
//                TrackProductSeller.builder()
//                        .numberOfProductsSold(product.getProductSeller()
//                                .getNumberOfProductsSold()).build() : null;
//
//        List<StockResponse> stockDtos = product.getStocks() != null ?
//                product.getStocks().stream().map(e -> {
//                    return StockServiceImpl.getStockResponse(e);
//                }).collect(Collectors.toList())
//                : new ArrayList<>();
//
//        List<EvaluationDto> evaluations = product.getEvaluations() != null ?
//                product.getEvaluations().stream().map(e ->
//                        (EvaluationDto) Convert.EVAL.toDto(e)).collect(Collectors.toList())
//                : new ArrayList<>();
//
//        MapName language = MapName.builder().nameEn(product.getLanguage().getNameEn()).nameVn(product.getLanguage().getNameVn()).build();
//
//        ProductDto productDto = ProductDto.builder()
//                .id(product.getId())
//                .description(product.getDescription())
//                .language(language)
//                .category((CategoryDto) Convert.CATE.toDto(product.getCategory()))
//                .stockResponses(stockDtos)
//                .evaluations(evaluations)
//                .productSeller(productSeller)
//                .vendorResponse(StockResponse.ProductResponse
//                        .VendorResponse.builder()
//                        .id(product.getVendor().getId())
//                        .name(product.getVendor().getShopName())
//                        .perMoneyDelivery(product.getVendor().getPerMoneyDelivery())
//                        .build())
//                .build();
        ProductDto productDto = mapper.map(product, ProductDto.class);
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
}
