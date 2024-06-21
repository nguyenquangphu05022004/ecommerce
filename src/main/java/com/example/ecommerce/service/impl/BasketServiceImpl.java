package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.domain.dto.product.BasketRequest;
import com.example.ecommerce.domain.dto.product.BasketDto;
import com.example.ecommerce.domain.Basket;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.User;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IBasketService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements IBasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public List<BasketDto> records() {
        List<Basket> baskets = basketRepository
                .findAllByUserUsername(SecurityUtils.username());
        return baskets.stream()
                .map(entity -> {
                    BasketDto basket = mapper.map(entity, BasketDto.class);
                    return basket;
                })
                .collect(Collectors.toList());

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
    public BasketDto saveOrUpdate(BasketRequest basketRequest) {
        Basket basket = null;
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        Optional<Basket> optionBasket = basketRepository
                .findByUserIdAndStockIdAndStockClassificationId
                        (
                                user.getId(),
                                basketRequest.getStockId(),
                                basketRequest.getStockClassificationId()
                        );
        if (optionBasket.isPresent()) {
            basket = optionBasket.get();
            if (basketRequest.getOperator() == '+') {
                basket = basket.toBuilder()
                        .quantity(basket.getQuantity() + 1)
                        .build();
            } else {
                if (basket.getQuantity() == 1) throw new RuntimeException("Number Of Product not zero");
                basket = basket.toBuilder()
                        .quantity(basket.getQuantity() - 1)
                        .build();
            }
            basketRepository.save(basket);
        } else {
            basket = Basket.builder()
                    .quantity(1)
                    .stock(Stock.builder()
                            .id(basketRequest.getStockId())
                            .build())
                    .stockClassification(StockClassification.builder()
                            .id(basketRequest.getStockClassificationId())
                            .build())
                    .user(user)
                    .build();
            basketRepository.save(basket);
        }
        return mapper.map(basket, BasketDto.class);
    }
}
