package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.EntityType;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.StockRepository;
import com.example.ecommerce.service.IFilesStorageService;
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
    private final IFilesStorageService filesStorageService;
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
        Stock stock = mapper.toEntity(stockRequest);
        if(stock.getId() != null) {
            filesStorageService.deleteImage(stock.getImages());
            Stock old = stockRepository
                    .findById(stock.getId())
                    .orElseThrow(() -> new GeneralException("Stock not found id " + stockRequest.getId()));
            filesStorageService.deleteImage(old.getImages());
            if(old.getColor() != null)
                ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getColorId(), "colorId");
            stock = mapper.toEntity(stockRequest, stock);
        }
        Stock saved = stockRepository.save(stock);
        stockRequest.getFileImages()
                .forEach(file -> filesStorageService.saveFile(
                        file,
                        saved.getId(),
                        EntityType.PRODUCT
                ));
    }

}
