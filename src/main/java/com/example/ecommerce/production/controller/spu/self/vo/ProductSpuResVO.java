package com.example.ecommerce.production.controller.spu.self.vo;

import com.example.ecommerce.production.controller.brand.vo.ProductBrandResVO;
import com.example.ecommerce.production.controller.category.vo.ProductCategoryResVO;
import com.example.ecommerce.production.controller.spu.self.vo.base.ProductSpuBaseVO;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import lombok.Data;

@Data
public class ProductSpuResVO extends ProductSpuBaseVO {
    private ProductCategoryResVO productCategory;
    private ProductBrandResVO productBrand;

    public ProductSpuResVO(ProductSpu productSpu) {

    }
}
