package com.example.ecommerce.controller;

import com.example.ecommerce.domain.dto.product.BasketDto;
import com.example.ecommerce.domain.dto.product.BasketRequest;
import com.example.ecommerce.service.IBasketService;
import com.example.ecommerce.service.impl.BasketServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
public class BasketController {

    private final IBasketService basketService;

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
    public BasketDto createBasket(@RequestBody BasketRequest request,
                             HttpServletResponse response) {
        var basket = basketService.saveOrUpdate(request);
        Cookie numberOfBasketCookie = new Cookie("basket", basketService.count().toString());
        numberOfBasketCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        numberOfBasketCookie.setPath("/");
        response.addCookie(numberOfBasketCookie);
        return basket;
    }
    @GetMapping("/baskets/{basketId}")
    @ResponseBody
    public void deleteBasket(@PathVariable("basketId") Long basketId, HttpServletResponse response) {
        basketService.delete(basketId);
        Cookie numberOfBasketCookie = new Cookie("basket", basketService.count().toString());
        numberOfBasketCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        numberOfBasketCookie.setPath("/");
        response.addCookie(numberOfBasketCookie);
    }
}
