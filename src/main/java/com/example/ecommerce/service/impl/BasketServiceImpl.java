package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Basket;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IBasketService;
import com.example.ecommerce.service.dto.BasketDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.BasketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements IBasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    @Qualifier("basketMapper")
    private final IMapper<Basket, BasketRequest, BasketDto> mapper;

    @Override
    public List<BasketDto> findAllByUser() {
        ValidationUtils.fieldCheckNullOrEmpty(SecurityUtils.username(), "Username");
        List<Basket> baskets = basketRepository
                .findAllByUserUsername(SecurityUtils.username());
        return mapper.toDtoList(baskets);

    }

    @Override
    public void delete(Long id) {
        basketRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return basketRepository.countAllByUserUsername(SecurityUtils.username());
    }


    @Override
    public void saveOrUpdate(BasketRequest basketRequest) {
        ValidationUtils.fieldCheckNullOrEmpty(basketRequest.getOperator(), "Operator");
        ValidationUtils.fieldCheckNullOrEmpty(basketRequest.getStockId(), "StockId");
        ValidationUtils.fieldCheckNullOrEmpty(basketRequest.getStockClassificationId(), "StockClassificationId");

        var user = userRepository.findByUsername(SecurityUtils.username())
                .orElseThrow(() -> new GeneralException("You are not login"));

        Optional<Basket> optionBasket = basketRepository
                .findByUserIdAndStockIdAndStockClassificationId
                        (
                                user.getId(),
                                basketRequest.getStockId(),
                                basketRequest.getStockClassificationId()
                        );
        Basket basket = null;
        if (optionBasket.isPresent()) {
            basket = optionBasket.get();
            if (basketRequest.getOperator().equals("+")) {
                basket = basket.toBuilder()
                        .quantity(basket.getQuantity() + 1)
                        .build();
            } else {
                if (basket.getQuantity() == 1)
                    throw new GeneralException("Number Of Product not zero");
                basket = basket.toBuilder()
                        .quantity(basket.getQuantity() - 1)
                        .build();
            }
        } else {
            basket = Basket.builder()
                    .quantity(1)
                    .stock(
                            Stock.builder()
                                    .id(basketRequest.getStockId())
                                    .build())
                    .stockClassification(
                            StockClassification.builder()
                                    .id(basketRequest.getStockClassificationId())
                                    .build())
                    .user(user)
                    .build();
        }
        basketRepository.save(basket);
    }
}
