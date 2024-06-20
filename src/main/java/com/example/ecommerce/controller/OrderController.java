package com.example.ecommerce.controller;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.StockClassification;
import com.example.ecommerce.domain.dto.ENUM.SelectFilterOrder;
import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.domain.dto.product.OrderDto;
import com.example.ecommerce.domain.dto.product.OrderRequest;
import com.example.ecommerce.domain.dto.product.StockResponse;
import com.example.ecommerce.domain.dto.user.UserResponseInfo;
import com.example.ecommerce.repository.StockClassificationRepository;
import com.example.ecommerce.service.IOrderService;
import com.example.ecommerce.service.IStockService;
import com.example.ecommerce.service.IUserService;
import com.example.ecommerce.service.impl.OrderServiceImpl;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;
    private final IUserService userService;
    private final IStockService stockService;
    private final StockClassificationRepository classificationRepository;
    @GetMapping("/orders/product/**")
    public String getFormCheckOut(@RequestParam("numberOfProduct") Integer numberOfProduct,
                                  @RequestParam("stockId") Long stockId,
                                  @RequestParam("stockType") Long stockClassificationId,
                                  Model model) {
        StockResponse stockDto = stockService.findById(stockId);
        UserResponseInfo userResponseInfo = userService
                .findUserByUsername(SecurityUtils.username());
        Optional<StockClassification> exist =
                classificationRepository.findById(stockClassificationId);
        if(exist.isPresent()) model.addAttribute("stockType", exist.get());
        model.addAttribute("stock", stockDto);
        model.addAttribute("user", userResponseInfo);
        model.addAttribute("totalPrice",
                SystemUtils.getFormatNumber((
                        numberOfProduct * stockDto.getPrice()
                        + stockDto.getProduct()
                        .getVendor()
                        .getPerMoneyDelivery())));
        model.addAttribute("numberOfProduct", numberOfProduct);
        return "order";
    }

    @GetMapping("/orders/{status}")
    @ResponseBody
    public List<OrderDto> getListOrderOfUser(@PathVariable("status") Status status) {
        return orderService.getAllOrderOfCustomer(status);
    }

    @DeleteMapping("/orders/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
    }

    @PutMapping("/orders/{orderId}/approval")
    @ResponseStatus(HttpStatus.OK)
    public void approvalOrder(@PathVariable("orderId") Long orderId) {
        orderService.approval(orderId);
    }

    @PostMapping("/orders")
    @ResponseBody
    public OrderDto createOrder(@RequestBody OrderRequest orderDto) {
        return orderService.saveOrUpdate(orderDto);
    }

    @GetMapping("/user/order")
    public String getOrderPageOfUser(Model model) {
        model.addAttribute("statusOrders", Arrays.asList(Status.values()));
        return "orders-user";
    }
    @GetMapping("/vendor/orders")
    public String getOrderPageVendor() {
        return "admin/order-product";
    }
    @GetMapping("/vendor/orders/product")
    @ResponseBody
    public List<OrderDto> getListOrder(@RequestParam("selectFilterOrder")
                                           SelectFilterOrder selectFilerOrder) {
        List<OrderDto> orders = orderService.getAllOrderOfVendor(selectFilerOrder);
        return orders;
    }
}
