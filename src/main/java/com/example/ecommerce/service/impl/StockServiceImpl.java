package com.example.ecommerce.service.impl;

import com.example.ecommerce.converter.impl.ProductConverterImpl;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.StockDto;
import com.example.ecommerce.dto.StockRequest;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Stock;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.StockRepository;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final IImageService imageService;
    private final ProductRepository productRepository;
    @Override
    public List<StockDto> records() {
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
    public StockDto findById(Long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(
                () -> new NotFoundException("StockId", id + "")
        );
        return StockDto.builder()
                .code(stock.getCode())
                .quantity(stock.getQuantity())
                .id(stock.getId())
                .imageDtos(ProductConverterImpl.getImageDtoByStock(stock))
                .productDto((ProductDto) Convert.PRO.toDto(stock.getProduct()))
                .build();
    }

    @Override
    @Transactional
    public void save(StockRequest stockRequest) {
        Product product = productRepository.findById(stockRequest.getProductId())
                        .orElseThrow(() -> new NotFoundException("ProductId", stockRequest.getProductId() + ""));
        Stock stock = Stock.builder()
                .code(stockRequest.getCode())
                .product(product)
                .quantity(stockRequest.getQuantity())
                .build();
        stock = stockRepository.save(stock);
        for(MultipartFile mul : stockRequest.getMultipartFiles()) {
            imageService.uploadFile(
                    mul,
                    SystemUtils.FOLDER_STOCK_IMAGE,
                    SystemUtils.SHORT_URL_STOCK,
                    stock
            );
        }
    }

    @Override
    public void update(Long stockId) {

    }
}
