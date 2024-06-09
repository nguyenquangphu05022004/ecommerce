package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import org.modelmapper.ModelMapper;

public class FactoryConverter {
    public static IGenericConverter getInstance(Converter converter) {
        ModelMapper mapper = new ModelMapper();
        IGenericConverter genericConverter = null;
        switch (converter) {
            case PRODUCT:
                genericConverter = new ProductConverterImpl();
                break;
            case CATEGORY:
                genericConverter = new CategoryConverter(mapper);
                break;
            case EVALUATION:
                genericConverter = new EvaluationConverter(mapper);
                break;
            case VENDOR:
                genericConverter = new VendorConverter(mapper);
                break;
            case USER:
                genericConverter = new UserMapper(mapper);
                break;
            case BASKET:
                genericConverter = new BasketConverterImpl();
                break;
        }
        return genericConverter;
    }
}
