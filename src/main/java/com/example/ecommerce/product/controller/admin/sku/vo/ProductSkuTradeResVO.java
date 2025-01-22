package com.example.ecommerce.product.controller.admin.sku.vo;

import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.collection.StreamUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import lombok.Data;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertListMap;
import static com.example.ecommerce.frame.common.collection.MapUtils.convertToMapSet;

@Data
public class ProductSkuTradeResVO {
    private Long id;
    private String name;
    private String image;
    private Integer price;
    private String properties;
    private Integer quantity;
    private Map<ProductPropertyVO, Set<Pair<ProductPropertyValueResVO, Boolean>>> propertiesMap;

    public ProductSkuTradeResVO(ProductSku sku) {
        this.id = sku.getId();
        this.name = sku.getProductSpu().getName();
        this.image = sku.getImage();
        this.price = sku.getPrice();
        this.quantity = sku.getQuantity();
        this.properties = sku.toProperties();
        this.propertiesMap = ProductSkuTradeResVO.mapProperties(sku);
    }

    private static Map<ProductPropertyVO, Set<Pair<ProductPropertyValueResVO, Boolean>>> mapProperties(ProductSku sku) {
        Set<ProductSku> productSkus = sku.getProductSpu().getProductSkus();
        return MapUtils.convertMap(mapProperties(productSkus), (property, propertiesValues) -> {
            return propertiesValues.stream().map(ss -> {
                return new Pair<>(ss, StreamUtils.filter(sku.getProductSkuProperties(), p -> p.getProductPropertyValue().getId().equals(ss.getId())));
            }).collect(Collectors.toSet());
        });
    }

    public static Map<ProductPropertyVO, Set<ProductPropertyValueResVO>> mapProperties( Set<ProductSku> productSkus) {

        Set<Map<ProductPropertyVO, Set<ProductPropertyValueResVO>>> collect = productSkus.stream().map(s -> {
            return convertToMapSet(convertSet(s.getProductSkuProperties(), ss -> {
                return new Pair<>(
                        new ProductPropertyVO(ss.getProductProperty()),
                        new ProductPropertyValueResVO(ss.getProductPropertyValue()));
            }));
        }).collect(Collectors.toSet());

        return convertListMap(collect);
    }
}
