package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
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
    public void saveOrUpdate(BasketRequest basketRequest) {
        Basket basket = null;
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        Optional<Basket> optionBasket = basketRepository
                .findByUserIdAndStockId
                        (
                                user.getId(),
                                basketRequest.getStockId()
                        );

        if (optionBasket.isPresent()) {
            basket = optionBasket.get();
            if(basket.getTotalPrice() <= 0) throw new RuntimeException("Total price is negative");
            else {
                if(basketRequest.getOperator() == '+') {
                    basket = basket.toBuilder()
                            .quantity(basket.getQuantity() + 1)
                            .build();
                } else {
                    basket = basket.toBuilder()
                            .quantity(basket.getQuantity() - 1)
                            .build();
                }
                basketRepository.save(basket);
            }
        } else {
            basket = Basket.builder()
                    .quantity(1)
                    .stock(Stock.builder()
                            .id(basketRequest.getStockId())
                            .build())
                    .user(user)
                    .build();
            basketRepository.save(basket);
        }
    }
}
