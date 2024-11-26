package com.example.ecommerce.production.controller.spu.vo;

import com.example.ecommerce.production.controller.brand.vo.ProductBrandResVO;
import com.example.ecommerce.production.controller.category.vo.ProductCategoryResVO;
import com.example.ecommerce.production.controller.spu.vo.base.ProductSpuBaseVO;
import lombok.Data;

@Data
public class ProductSpuResVO extends ProductSpuBaseVO {
    private ProductCategoryResVO productCategory;
    private ProductBrandResVO productBrand;
}
