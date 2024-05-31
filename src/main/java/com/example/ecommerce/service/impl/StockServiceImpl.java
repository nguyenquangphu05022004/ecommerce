package com.example.ecommerce.service.impl;

import com.example.ecommerce.converter.impl.ProductConverterImpl;
import com.example.ecommerce.dto.StockRequest;
import com.example.ecommerce.dto.StockResponse;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Decoration;
import com.example.ecommerce.entity.Stock;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.StockRepository;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final IImageService imageService;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;
    @Override
    public List<StockResponse> records() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    @Transactional
    public StockResponse findById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new NotFoundException("StockId", id + "")
        );
        return getStockResponse(stock);
    }


    @Override
    @Transactional
    public void save(StockRequest stockRequest) {
        Product product = productRepository.findById(stockRequest.getProductId())
                        .orElseThrow(() -> new NotFoundException("ProductId", stockRequest.getProductId() + ""));
        List<Decoration> decorations = stockRequest
                .getProductTypes()
                .stream()
                .map(
                        productTypeDto -> mapper.map(productTypeDto, Decoration.class)
                )
                .collect(Collectors.toList());

        List<Image> images = stockRequest.getMultipartFiles()
                .stream()
                .map(multipartFile -> imageService.uploadFile(
                        multipartFile,
                        SystemUtils.FOLDER_STOCK_IMAGE,
                        SystemUtils.SHORT_URL_STOCK
                ))
                .collect(Collectors.toList());

        Stock stock = Stock.builder()
                .code(stockRequest.getCode())
                .product(product)
                .price(stockRequest.getPrice())
                .quantityOfProduct(stockRequest.getQuantity())
                .decorations(decorations)
                .images(images)
                .build();
        stockRepository.save(stock);
    }

    @Override
    public void update(Long stockId) {

    }



    public static StockResponse getStockResponse(Stock stock) {
        return StockResponse.builder()
                .code(stock.getCode())
                .id(stock.getId())
                .images(ProductConverterImpl.getImageDtoByStock(stock))
                .productResponse( //mapper product
                        StockResponse
                                .ProductResponse
                                .builder()
                                .name(stock.getProduct()
                                        .getLanguage()
                                        .getNameVn())
                                .description(stock.getProduct()
                                        .getDescription())
                                .id(stock.getProduct()
                                        .getId())
                                .vendorResponse( //mapper vendor
                                        StockResponse
                                                .ProductResponse
                                                .VendorResponse
                                                .builder()
                                                .id(stock.getProduct()
                                                        .getVendor()
                                                        .getId())
                                                .name(stock.getProduct()
                                                        .getVendor()
                                                        .getShopName())
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}
