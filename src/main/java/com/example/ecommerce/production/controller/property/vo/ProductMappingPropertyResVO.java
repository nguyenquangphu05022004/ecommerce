package com.example.ecommerce.production.controller.property.vo;

import com.example.ecommerce.production.dal.dataobject.properties.ProductProperty;
import lombok.Data;

@Data
public class ProductMappingPropertyResVO {
    private ProductPropertyVO productProperty;
    private ProductPropertyValueResVO propertyValue;
}
