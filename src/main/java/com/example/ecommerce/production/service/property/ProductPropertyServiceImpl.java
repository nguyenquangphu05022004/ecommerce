package com.example.ecommerce.production.service.property;

import com.example.ecommerce.production.controller.property.vo.ProductPropertyVO;
import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPropertyServiceImpl implements ProductPropertyService{
    @Override
    public ProductProperty createProductProperty(ProductPropertyVO reqVO) {
        return null;
    }

    @Override
    public ProductProperty updateProductProperty(ProductPropertyVO reqVO) {
        return null;
    }

    @Override
    public List<ProductProperty> getListProductPropertyByOwner(Long userOwnerId) {
        return null;
    }
}
