package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.Image;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.domain.dto.ENUM.Size;
import com.example.ecommerce.domain.dto.product.StockRequest;
import com.example.ecommerce.domain.dto.product.StockResponse;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final IImageService imageService;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    @Override
    public List<StockResponse> getAll() {
        return null;
    }

    @Override
    public void delete(Long id) {

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
        String[] words = stockRequest.getFormatClassification()
                .replaceAll("\\s+", "")
                .split(",");
        List<StockClassification> stockClassifications = Arrays.stream(words)
                .map(word -> {
                    //quantity;size,quantity;size
                    try {
                        String[] pairs = word.split(";");
                        return new StockClassification(
                                Integer.parseInt(pairs[0]),
                                Size.valueOf(pairs[1]),
                                0
                        );
                    } catch (Exception e) {
                        System.out.println("Error convert formatClassification to Enum or Number");
                        throw e;
                    }
                })
                .collect(Collectors.toList());
        Stock stock = null;
        List<Image> images = null;
        if (stockRequest.getId() != null &&
                stockRequest.getMultipartFiles().get(0).isEmpty()) {
            stock = stockRepository.findById(stockRequest.getId())
                    .orElseThrow(() -> new NotFoundException(
                            "StockId",
                            stockRequest.getId() + ""
                    ));
            images = stock.getImages();
        } else {
            images = stockRequest.getMultipartFiles()
                    .stream()
                    .map(multipartFile -> {
                        Optional<Image> image = imageService
                                .loadByFileName(
                                        multipartFile.getOriginalFilename()
                                );
                        if (image.isEmpty()) {
                            return imageService.uploadFile(
                                    multipartFile,
                                    SystemUtils.TAG
                            );
                        }
                        return image.get();
                    })
                    .collect(Collectors.toList());
        }
        if(stock == null) {
            stock = new Stock();
        }
        stock = stock.toBuilder()
                .stockClassifications(stockClassifications)
                .code(stockRequest.getCode())
                .images(images)
                .price(stockRequest.getPrice())
                .product(product)
                .color(stockRequest.getColor())
                .id(stockRequest.getId())
                .build();
        stockRepository.save(stock);
    }

    @Override
    public void update(Long stockId) {

    }
}
