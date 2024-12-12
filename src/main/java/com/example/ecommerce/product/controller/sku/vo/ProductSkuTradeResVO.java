package com.example.ecommerce.product.controller.sku.vo;

import com.example.ecommerce.frame.common.collection.StreamUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.product.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.controller.property.vo.ProductPropertyValueResVO;
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
        this.properties = StringUtils.convertToString(sku.getProductSkuProperties(), s -> s.getProductPropertyValue().getPropertyValue(), ", ");
        this.propertiesMap = mapProperties(sku);
    }

    private Map<ProductPropertyVO, Set<Pair<ProductPropertyValueResVO, Boolean>>> mapProperties(ProductSku sku) {
        Set<ProductSku> productSkus = sku.getProductSpu().getProductSkus();

        Set<Map<ProductPropertyVO, Set<Pair<ProductPropertyValueResVO, Boolean>>>> collect = productSkus.stream().map(s -> {
            return convertToMapSet(convertSet(s.getProductSkuProperties(), ss -> {
                return new Pair<>(new ProductPropertyVO(ss.getProductProperty()),
                        new Pair<>(
                                new ProductPropertyValueResVO(ss.getProductPropertyValue()),
                                StreamUtils.filter(sku.getProductSkuProperties(), p ->  p.getProductPropertyValue().equals(ss.getProductPropertyValue()))
                        ));
            }));
        }).collect(Collectors.toSet());

        return convertListMap(collect);
    }
}
