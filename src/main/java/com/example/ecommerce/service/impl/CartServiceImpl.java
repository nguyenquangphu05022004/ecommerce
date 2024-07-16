package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Stock;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.StockRepository;
import com.example.ecommerce.service.ICartService;
import com.example.ecommerce.service.dto.StockDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.CartRequest;
import com.example.ecommerce.service.request.StockRequest;
import com.example.ecommerce.service.response.ItemResponse;
import com.example.ecommerce.service.response.ShoppingCartResponse;
import com.example.ecommerce.service.response.VendorCartResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService {

    private final StockRepository stockRepository;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    @Qualifier("stockMapper")
    private final IMapper<Stock, StockRequest, StockDto> stockMapper;
    private final String USER_KEY = "USER_%s_CART";
    private final String VENDOR_ITEM_PRODUCT = "USER_%s_VENDOR_ITEM_PRODUCT_%s";
    private final String VENDOR_KEY = "USER_%s_VENDOR_%s";
    private final String STOCK_KEY = "STOCK_%s";

    @Override
    public void add(CartRequest cartRequest) {
        try {
            ValidationUtils.fieldCheckNullOrEmpty(cartRequest.getStockId(), "stockId");

            Stock stock = stockRepository.findById(cartRequest.getStockId())
                    .orElseThrow(() -> new GeneralException(
                            String.format("Stock with id %s not found", cartRequest.getStockId())
                    ));
            Vendor vendor = stock.getProduct().getVendor();

            RedisKey redisKey = new RedisKey(
                    String.format(VENDOR_KEY, SecurityUtils.username(), vendor.getId()),
                    String.format(VENDOR_ITEM_PRODUCT,SecurityUtils.username(), vendor.getId()),
                    String.format(STOCK_KEY, cartRequest.getStockId())
            );
            redisTemplate.opsForSet().add(
                    getUserKey(),
                    objectMapper.writeValueAsString(redisKey));

            Boolean isKeyExists = redisTemplate.opsForHash().hasKey(
                    redisKey.getVendorItemProductKey(),
                    redisKey.getStockKey()
            );
            ItemResponse response;
            if (!isKeyExists) {
                response = ItemResponse.builder()
                        .quantity(1)
                        .stock(stockMapper.toDto(stock))
                        .createdAt(LocalDateTime.now())
                        .build();
            } else {
                String json = (String) redisTemplate.opsForHash().get(
                        redisKey.getVendorItemProductKey(),
                        redisKey.getStockKey()
                );
                response = objectMapper.readValue(json, ItemResponse.class);
                if (cartRequest.getOperation().equals("+")) {
                    response.setQuantity(response.getQuantity() + 1);
                } else {
                    if (response.getQuantity() == 1)
                        throw new GeneralException("You can't decrease quantity of product to 0");
                    response.setQuantity(response.getQuantity() - 1);
                }
            }
            redisTemplate.opsForHash().put(
                    redisKey.getVendorItemProductKey(),
                    redisKey.getStockKey(),
                    objectMapper.writeValueAsString(response)
            );

            VendorCartResponse vendorResponse = new VendorCartResponse();
            setInfoVendor(vendorResponse, stock);

            if (!redisTemplate.opsForHash().hasKey(
                    "VENDOR",
                    redisKey.getVendorKey())) {
                redisTemplate.opsForHash().put(
                        "VENDOR",
                        redisKey.getVendorKey(),
                        objectMapper.writeValueAsString(vendorResponse)
                );
            }
        } catch (JsonProcessingException ex) {
            throw new GeneralException(ex.getMessage());
        }
    }


    @Override
    public ShoppingCartResponse getShoppingCart() {
        List<RedisKey> redisKeys = getValueKeyUser();
        ShoppingCartResponse res = new ShoppingCartResponse();
        if (redisKeys == null) return res;
        redisKeys.forEach(redisKey -> {
            List<ItemResponse> itemResponses = getListItemResponseWithVendorItemProductKey(redisKey.getVendorItemProductKey());
            VendorCartResponse vendorResponse = getVendorResponseWithKetVendorKey(redisKey.getVendorKey());
            vendorResponse.setItemResponses(itemResponses);
            res.getVendors().add(vendorResponse);
        });
        return res;
    }

    @Override
    public void delete(Long stockId, Long vendorId) {
        if(redisTemplate.opsForHash().hasKey(
                String.format(VENDOR_ITEM_PRODUCT, SecurityUtils.username(), vendorId),
                String.format(STOCK_KEY, stockId)
        )) {
            redisTemplate.opsForHash().delete(
                    String.format(VENDOR_ITEM_PRODUCT, SecurityUtils.username(), vendorId),
                    String.format(STOCK_KEY, stockId)
            );
        }
    }

    private String getUserKey() {
        return String.format(USER_KEY, SecurityUtils.username());
    }

    private void setInfoVendor(VendorCartResponse valueOfKeyVendor, Stock stock) {
        var vendor = stock.getProduct().getVendor();
        valueOfKeyVendor.setId(vendor.getId());
        valueOfKeyVendor.setPerMoneyDelivery(vendor.getPerMoneyDelivery());
        valueOfKeyVendor.setShopName(vendor.getShopName());
    }

    private List<RedisKey> getValueKeyUser() {
        Set<String> members = redisTemplate.opsForSet().members(getUserKey());
        if (members == null) {
            return null;
        }
        return members.stream().map(json -> {
            try {
                RedisKey redisKey = objectMapper.readValue(json, RedisKey.class);
                return redisKey;
            } catch (JsonProcessingException e) {
                throw new GeneralException(e.getMessage());
            }
        }).collect(Collectors.toList());
    }

    private VendorCartResponse getVendorResponseWithKetVendorKey(String vendorKey) {
        String json = (String) redisTemplate.opsForHash().get("VENDOR", vendorKey);
        try {
            return objectMapper.readValue(json, VendorCartResponse.class);
        } catch (JsonProcessingException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    private List<ItemResponse> getListItemResponseWithVendorItemProductKey(String vendorItemProductKey) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(vendorItemProductKey);
        return entries.values().stream().map(object -> {
                    String json = (String) object;
                    try {
                        ItemResponse itemResponse = objectMapper.readValue(json, ItemResponse.class);
                        return itemResponse;
                    } catch (JsonProcessingException e) {
                        throw new GeneralException(e.getMessage());
                    }
                }).sorted((it1, it2) -> it1.getCreatedAt().compareTo(it2.getCreatedAt()))
                .collect(Collectors.toList());
    }
}

@Getter
@NoArgsConstructor
@Setter
class RedisKey {
    private String vendorKey;
    private String vendorItemProductKey;
    private String stockKey;

    public RedisKey(String vendorKey,
                    String vendorItemProductKey,
                    String stockKey) {
        this.vendorKey = vendorKey;
        this.vendorItemProductKey = vendorItemProductKey;
        this.stockKey = stockKey;
    }

}
