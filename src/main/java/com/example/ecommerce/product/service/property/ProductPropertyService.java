package com.example.ecommerce.product.service.property;

import com.example.ecommerce.frame.common.pojo.PageParam;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyVO;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;

import java.util.List;

public interface ProductPropertyService {
    ProductProperty createProductProperty(ProductPropertyVO reqVO);
    ProductProperty updateProductProperty(ProductPropertyVO reqVO);

    ProductProperty getById(Long id);

    PageResult<ProductProperty> getPageProperty(PageParam req);

    PageResult<ProductProperty> getPagePropertyByOwnerId(Long userId, PageParam req);
}
