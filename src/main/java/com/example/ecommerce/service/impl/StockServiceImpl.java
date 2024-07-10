package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.domain.*;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.ColorRepository;
import com.example.ecommerce.repository.SizeRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockRepository stockRepository;
    private final IFilesStorageService filesStorageService;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> mapper;

    @Override
    public void delete(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public StockDto findById(Long id) {
        Optional<Stock> optionalStock = stockRepository.findById(id);
        if (optionalStock.isPresent()) {
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

        List<StockClassification> stockClassifications =
                stockRequest.getStockClassifications().stream()
                        .map(st -> {
                            Size size = null;
                            if (stockRequest.getProductType() == ProductType.PRODUCT_HAS_COLOR) {
                                ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getColorId(), "Color Id");
                            } else if (stockRequest.getProductType() == ProductType.PRODUCT_HAS_SIZE) {
                                ValidationUtils.fieldCheckNullOrEmpty(st.getSizeName(), "Classification SizeName");
                            } else if (stockRequest.getProductType() == ProductType.PRODUCT_NOT_COLOR_SIZE) {

                            } else if (stockRequest.getProductType() == ProductType.PRODUCT_HAVE_COLOR_SIZE) {
                                ValidationUtils.fieldCheckNullOrEmpty(stockRequest.getColorId(), "Color Id");
                                ValidationUtils.fieldCheckNullOrEmpty(st.getSizeName(), "Classification SizeName");
                            }
                            return StockClassification.builder()
                                    .seller(0)
                                    .quantityOfProduct(st.getQuantityOfProduct())
                                    .size(size)
                                    .build();
                        }).collect(Collectors.toList());

        Stock stock = mapper.toEntity(stockRequest).toBuilder()
                .stockClassifications(stockClassifications)

                .build();
        if (stock.getId() != null) {
            filesStorageService.deleteImage(stock.getImages());
            Stock old = stockRepository
                    .findById(stock.getId())
                    .orElseThrow(() -> new GeneralException("Stock not found id " + stockRequest.getId()));
            filesStorageService.deleteImage(old.getImages());
            if (old.getColor() != null)
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
