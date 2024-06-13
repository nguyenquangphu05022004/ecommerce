package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.Decoration;
import com.example.ecommerce.domain.Image;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.dto.product.StockRequest;
import com.example.ecommerce.domain.dto.product.StockResponse;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.DecorationRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final IImageService imageService;
    private final ProductRepository productRepository;
    private final DecorationRepository decorationRepository;
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
    public StockResponse findById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new NotFoundException("StockId", id + "")
        );
        return mapper.map(stock, StockResponse.class);
    }


    @Override
    @Transactional
    public void save(StockRequest stockRequest) {
        Product product = productRepository.findById(
                        stockRequest.getProductId()
        ).orElseThrow(() -> new NotFoundException(
                                "ProductId", stockRequest.getProductId() + ""
                        ));
        Decoration decoration = decorationRepository.findById(
                stockRequest.getDecorationId()
        ).orElseThrow(() -> new NotFoundException(
                        "DecorationId", stockRequest.getDecorationId() + ""
                ));

        List<Image> images = stockRequest.getMultipartFiles()
                .stream()
                .map(multipartFile -> {
                    Optional<Image> image = imageService
                            .loadByFileName(
                                    multipartFile.getOriginalFilename()
                            );
                    if(image.isEmpty()) {
                        return imageService.uploadFile(
                                multipartFile,
                                SystemUtils.TAG
                        );
                    }
                    return image.get();
                })
                .collect(Collectors.toList());

        Stock stock = Stock.builder()
                .code(stockRequest.getCode())
                .product(product)
                .price(stockRequest.getPrice())
                .quantityOfProduct(stockRequest.getQuantityOfProduct())
                .decoration(decoration)
                .images(images)
                .build();
        stockRepository.save(stock);
    }

    @Override
    public void update(Long stockId) {

    }
}
