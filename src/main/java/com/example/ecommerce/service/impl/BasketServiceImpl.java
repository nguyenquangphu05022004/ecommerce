package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.IBasketService;
import com.example.ecommerce.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements IGenericService<BasketDto>, IBasketService {
    private final BasketRepository basketRepository;
    private final UserRepository userRepository;

    @Autowired
    public BasketServiceImpl(BasketRepository basketRepository, UserRepository userRepository) {
        this.basketRepository = basketRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BasketDto> records() {
        return basketRepository
                .findAllByUserUsername(SecurityUtils.username())
                .stream()
                .map(entity -> (BasketDto) Convert.BASKET.toDto(entity))
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
    public BasketDto findById(Long id) {
        return null;
    }

    @Override
    public BasketDto saveOrUpdate(BasketDto basketDto) {
        User user = userRepository.findByUsername(SecurityUtils.username()).get();
        Basket basket = (Basket) Convert.BASKET.toEntity(basketDto);
        if(basketDto.getId() != null) {
            basket.setQuantity(basketDto.getQuantity());
        }
        basket.setUser(user);
        return (BasketDto) Convert.BASKET.toDto(
                basketRepository.save(basket)
        );
    }
}
