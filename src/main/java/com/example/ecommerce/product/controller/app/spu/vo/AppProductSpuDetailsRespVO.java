package com.example.ecommerce.product.controller.app.spu.vo;

import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.collection.StreamUtils;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.object.ObjectUtils;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.statistic.controller.product.vo.ProductStatisticSimpleRespVO;
import com.example.ecommerce.statistic.dal.dataobject.product.ProductStatistic;
import com.example.ecommerce.system.controller.admin.user.vo.SellerDetailsRespVO;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.ecommerce.frame.common.collection.CollUtils.convertList;
import static com.example.ecommerce.frame.common.collection.CollUtils.convertSet;
import static com.example.ecommerce.frame.common.collection.StreamUtils.mapLong;

@Data
public class AppProductSpuDetailsRespVO extends AppProductSpuSimpleRespVO{
    private List<String> sliders;
    private ProductStatisticSimpleRespVO statistic;
    private SellerDetailsRespVO seller;
//    private List<AppProductSkuRespVO> skus;
    private String description;

    private Set<Pair<ProductPropertyVO, Set<ProductPropertyValueResVO>>> properties;
    private Set<Pair<Long, AppProductSkuRespVO>> skus;

    public AppProductSpuDetailsRespVO(ProductSpu spu, Discount discount, ProductStatistic statistic) {
        super(spu, discount, statistic);
        this.sliders = convertList(spu.getProductSkus(), ProductSku::getImage);
        this.statistic = ObjectUtils.get(statistic, ProductStatisticSimpleRespVO::new);
        this.seller = null;
//        this.skus = CollUtils.convertList(spu.getProductSkus(), AppProductSkuRespVO::new);
        this.properties = getAllPropertyOfSku(spu.getProductSkus());
        this.description = spu.getDescription();
        this.skus = convertSet(spu.getProductSkus(), sku -> {
            Long sumIdPropertyValue = mapLong(sku.getProductSkuProperties(), skuProperty -> {
                return skuProperty.getProductPropertyValue().getId();
            }).sum();
            return new Pair<>(sumIdPropertyValue, new AppProductSkuRespVO(sku));
        });
    }

    private Set<Pair<ProductPropertyVO, Set<ProductPropertyValueResVO>>> getAllPropertyOfSku(Set<ProductSku> productSkus) {
        Set<ProductProperty> properties = convertSet(convertSet(productSkus, sku -> {
            return convertSet(sku.getProductSkuProperties(), ProductSkuProperty::getProductProperty);
        }));

        Set<ProductPropertyValue> propertyValues =  convertSet(convertSet(productSkus, sku -> {
            return convertSet(sku.getProductSkuProperties(), ProductSkuProperty::getProductPropertyValue);
        }));

        Set<Pair<ProductPropertyVO, Set<ProductPropertyValueResVO>>> res = new HashSet<>();
        properties.forEach(property -> {
            Set<ProductPropertyValueResVO> set = StreamUtils
                    .filterAndThen(propertyValues, value -> value.getProductProperty().equals(property))
                    .map(ProductPropertyValueResVO::new)
                    .collect(Collectors.toSet());
            res.add(new Pair<>(new ProductPropertyVO(property), set));
        });

        return res;
    }

}
