package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.NotificationRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.StockRepository;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.StockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final IImageService imageService;
    private final ProductRepository productRepository;
    private final NotificationRepository notificationRepository;
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> mapper;

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public StockDto findById(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if(optionalStock.isPresent()) {
            return mapper.toDto(optionalStock.get());
        }
        throw new GeneralException(String.format("Stock id %s not found", id));
    }

    @Override
    @Transactional
    public void save(StockRequest stockRequest) {
        ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getProductId(), "productId");
        ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getPrice(), "price");
        ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getCode(), "code");
        var stock = mapper.toEntity(stockRequest);
        if(stock.getId() != null) {
            Stock old = stockRepository.findById(stock.getId()).get();
            if(old.getColor() != null)
                ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getColorId(), "colorId");
            stock = mapper.toEntity(stockRequest, stock);
        }
        stockRepository.save(stock);
    }

}
