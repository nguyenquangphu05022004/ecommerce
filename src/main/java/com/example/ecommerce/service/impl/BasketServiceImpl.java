package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.BasketRequest;
import com.example.ecommerce.entity.ProductType;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.exception.NotValidException;
import com.example.ecommerce.repository.ProductTypeRepository;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IBasketService;
import com.example.ecommerce.service.IGenericService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
                .map(entity -> mapper.map(entity, BasketDto.class))
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
                .findByUserIdAndProductTypeId
                        (
                                user.getId(),
                                basketRequest.getProductTypeId()
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
                    .quantity(basket.getQuantity())
                    .productType(ProductType.builder()
                            .id(basketRequest.getProductTypeId())
                            .build())
                    .user(user)
                    .build();
            basketRepository.save(basket);
        }
    }
}
