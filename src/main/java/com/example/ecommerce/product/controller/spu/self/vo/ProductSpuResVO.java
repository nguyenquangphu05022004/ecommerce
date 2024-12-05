package com.example.ecommerce.product.controller.spu.self.vo;

import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.product.controller.brand.vo.ProductBrandResVO;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryResVO;
import com.example.ecommerce.product.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.controller.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.controller.spu.self.vo.base.ProductSpuBaseVO;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import lombok.Data;

import java.util.List;
import java.util.Map;

import static com.example.ecommerce.frame.common.collection.CollUtils.*;
import static com.example.ecommerce.frame.common.collection.MapUtils.*;

@Data
public class ProductSpuResVO extends ProductSpuBaseVO {
    private Long id;
    private ProductCategoryResVO productCategory;
    private ProductBrandResVO productBrand;
    private List<String> slider;
    private Map<ProductPropertyVO, ProductPropertyValueResVO> productSpuDetails;

    private Double rating;
    private Integer numberOfComment;
    private Integer numberOfSales;

    public ProductSpuResVO(ProductSpu productSpu) {
        super(productSpu);
        this.id = productSpu.getId();
        this.productBrand = new ProductBrandResVO(productSpu.getProductBrand());
        this.productCategory = new ProductCategoryResVO(productSpu.getProductCategory());
        this.slider = convertList(productSpu.getProductSkus(), sku -> sku.getImage());
        this.productSpuDetails = convertToMap(convertList(productSpu.getProductSpuDetails(), detail -> {
                    return new Pair<>(new ProductPropertyVO(detail.getProductProperty()),
                            new ProductPropertyValueResVO(detail.getProductPropertyValue()));}));
        this.rating = null;
        this.numberOfComment = null;
        this.numberOfSales = null;
    }

}
