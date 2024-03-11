package com.example.ecommerce.controller;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.service.impl.BasketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
public class BasketController {

    private final BasketServiceImpl basketService;

    @Autowired
    public BasketController(BasketServiceImpl basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/user/my-basket")
    public String getMyBasket(Model model) {
        List<BasketDto> baskets = basketService.records();
        model.addAttribute("baskets", baskets);
        return "basket";
    }

    @PostMapping("/baskets")
    @ResponseBody
    public BasketDto createBasket(@RequestBody BasketDto basketDto) {
        return basketService.saveOrUpdate(basketDto);
    }

    @PutMapping("/baskets")
    @ResponseBody
    public BasketDto updateBasket(@RequestBody BasketDto basketDto) {
        return basketService.saveOrUpdate(basketDto);
    }
    @DeleteMapping("/baskets/{basketId}")
    @ResponseBody
    public void deleteBasket(@PathVariable("basketId") Long basketId) {
        basketService.delete(basketId);
    }
}
