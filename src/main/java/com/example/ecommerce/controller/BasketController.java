package com.example.ecommerce.controller;

import com.example.ecommerce.dto.BasketDto;
import com.example.ecommerce.service.impl.BasketServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
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
    public BasketDto createBasket(@RequestBody BasketDto basketDto, HttpServletResponse response) {
        basketDto = basketService.saveOrUpdate(basketDto);
        Cookie numberOfBasketCookie = new Cookie("basket", basketService.count().toString());
        numberOfBasketCookie.setMaxAge(10 * 365 * 24 * 60 * 60);
        numberOfBasketCookie.setPath("/");
        response.addCookie(numberOfBasketCookie);
        return basketDto;
    }

    @PutMapping("/baskets")
    @ResponseBody
    public BasketDto updateBasket(@RequestBody BasketDto basketDto) {
        return basketService.saveOrUpdate(basketDto);
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
