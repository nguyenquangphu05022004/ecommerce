package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.ProductInventory;
import com.example.ecommerce.domain.model.binding.CartRequest;
import com.example.ecommerce.domain.model.modelviews.cart.ItemCartModelView;
import com.example.ecommerce.domain.model.modelviews.cart.VendorCartModelView;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.InventoryRepository;
import com.example.ecommerce.service.ICartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements ICartService {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;
    private final InventoryRepository inventoryRepository;
    private final String USER_KEY = "USER_%s_CART";
    private final String VENDOR_ITEM_PRODUCT = "USER_%s_VENDOR_ITEM_PRODUCT_%s";
    private final String VENDOR_KEY = "USER_%s_VENDOR_%s";
    private final String INVENTORY_KEY = "INVENTORY_%s";

    @Override
    public void add(CartRequest cartRequest, HttpServletRequest servletRequest) {
        try {
            ProductInventory inventory = inventoryRepository
                    .findById(cartRequest.getInventoryId())
                    .orElseThrow(() -> new GeneralException("Id not found"));
            Vendor vendor = inventory.getProduct().getVendor();

            String username =  SecurityUtils.getUsername();

            RedisKey redisKey = new RedisKey(
                    String.format(VENDOR_KEY, username, vendor.getId()),
                    String.format(VENDOR_ITEM_PRODUCT,username, vendor.getId()),
                    String.format(INVENTORY_KEY, inventory.getId())
            );
            /**
             * store all key
             */
            redisTemplate.opsForSet().add(
                    getUserKey(servletRequest),
                    objectMapper.writeValueAsString(redisKey)
            );

            Boolean isKeyExists = redisTemplate.opsForHash().hasKey(
                    redisKey.getVendorItemProductKey(),
                    redisKey.getInventoryKey()
            );
            /**
             * store item
             */
            ItemCartModelView itemCartModelView;
            if (!isKeyExists) {
                itemCartModelView = new ItemCartModelView(inventory, cartRequest.getQuantity());
            } else {
                String json = (String) redisTemplate.opsForHash().get(
                        redisKey.getVendorItemProductKey(),
                        redisKey.getInventoryKey()
                );
                itemCartModelView = objectMapper.readValue(json, ItemCartModelView.class);
                itemCartModelView.setQuantity(cartRequest.getQuantity());
            }
            redisTemplate.opsForHash().put(
                    redisKey.getVendorItemProductKey(),
                    redisKey.getInventoryKey(),
                    objectMapper.writeValueAsString(itemCartModelView)
            );


            /**
             * Store vendor
             */
            VendorCartModelView vendorCartModelView = new VendorCartModelView(vendor);
            if (!redisTemplate.opsForHash().hasKey(
                    "VENDOR",
                    redisKey.getVendorKey())) {
                redisTemplate.opsForHash().put(
                        "VENDOR",
                        redisKey.getVendorKey(),
                        objectMapper.writeValueAsString(vendorCartModelView)
                );
            }
        } catch (JsonProcessingException ex) {
            throw new GeneralException(ex.getMessage());
        }
    }


    @Override
    public List<VendorCartModelView> getShoppingCart(HttpServletRequest servletRequest) {
        List<RedisKey> redisKeys = getValueKeyUser(servletRequest);
        List<VendorCartModelView> res = new ArrayList<>();
        if (redisKeys == null) return res;
        redisKeys.forEach(redisKey -> {
            List<ItemCartModelView> itemResponses = getListItemResponseWithVendorItemProductKey(redisKey.getVendorItemProductKey());
            VendorCartModelView vendorResponse = getVendorResponseWithKetVendorKey(redisKey.getVendorKey());
            vendorResponse.setItems(itemResponses);
            res.add(vendorResponse);
        });
        return res;
    }

    @Override
    public void delete(Long inventory, Long vendorId, HttpServletRequest servletRequest) {
        String keyUser =  SecurityUtils.getUsername();
        if(redisTemplate.opsForHash().hasKey(
                String.format(VENDOR_ITEM_PRODUCT,keyUser, vendorId),
                String.format(INVENTORY_KEY, inventory)
        )) {
            redisTemplate.opsForHash().delete(
                    String.format(VENDOR_ITEM_PRODUCT, keyUser, vendorId),
                    String.format(INVENTORY_KEY, inventory)
            );
        } else {
            throw new GeneralException("You haven't item in cart");
        }
    }

    private String getUserKey(HttpServletRequest servletRequest) {
        String keyUser = SecurityUtils.getUsername() == null ? servletRequest.getRemoteAddr() : SecurityUtils.getUsername();
        return String.format(USER_KEY, keyUser);
    }




    private List<RedisKey> getValueKeyUser(HttpServletRequest servletRequest) {
        Set<String> members = redisTemplate.opsForSet().members(getUserKey(servletRequest));
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

    private VendorCartModelView getVendorResponseWithKetVendorKey(String vendorKey) {
        String json = (String) redisTemplate.opsForHash().get("VENDOR", vendorKey);
        try {
            return objectMapper.readValue(json, VendorCartModelView.class);
        } catch (JsonProcessingException e) {
            throw new GeneralException(e.getMessage());
        }
    }

    private List<ItemCartModelView> getListItemResponseWithVendorItemProductKey(String vendorItemProductKey) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(vendorItemProductKey);
        return entries.values().stream().map(object -> {
                    String json = (String) object;
                    try {
                        ItemCartModelView itemResponse = objectMapper.readValue(json, ItemCartModelView.class);
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
    private String inventoryKey;

    public RedisKey(String vendorKey,
                    String vendorItemProductKey,
                    String inventoryKey) {
        this.vendorKey = vendorKey;
        this.vendorItemProductKey = vendorItemProductKey;
        this.inventoryKey = inventoryKey;
    }

}
