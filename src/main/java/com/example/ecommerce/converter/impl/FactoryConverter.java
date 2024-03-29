package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;
import org.modelmapper.ModelMapper;

public class FactoryConverter {
    public static IGenericConverter getInstance(Converter converter) {
        ModelMapper mapper = new ModelMapper();
        IGenericConverter genericConverter = null;
        switch (converter) {
            case BILL:
                genericConverter =  new BillConverterImpl();
                break;
            case PRODUCT:
                genericConverter = new ProductConverterImpl(mapper);
                break;
            case CATEGORY:
                genericConverter = new CategoryConverter(mapper);
                break;
            case ORDER:
                genericConverter = new OrderConverter(mapper);
                break;
            case EVALUATION:
                genericConverter = new EvaluationConverter(mapper);
                break;
            case VENDOR:
                genericConverter = new VendorConverter(mapper);
                break;
            case USER:
                genericConverter = new UserConverterImpl(mapper);
                break;
            case BASKET:
                genericConverter = new BasketConverterImpl(mapper);
                break;
        }
        return genericConverter;
    }
}
