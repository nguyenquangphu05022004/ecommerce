package com.example.ecommerce.converter.impl;

import com.example.ecommerce.converter.IGenericConverter;

public class FactoryConverter {
    public static IGenericConverter getInstance(Converter converter) {
        IGenericConverter genericConverter = null;
        switch (converter) {
            case BILL:
                genericConverter =  new BillConverterImpl();
                break;
            case PRODUCT:
                genericConverter = new ProductConverterImpl();
                break;
            case CATEGORY:
                genericConverter = new CategoryConverter();
                break;
            case ORDER:
                genericConverter = new OrderConverter();
                break;
            case EVALUATION:
                genericConverter = new EvaluationConverter();
                break;
            case VENDOR:
                genericConverter = new VendorConverter();
                break;
            case CUSTOMER:
                genericConverter = new CustomerConverter();
                break;
        }
        return genericConverter;
    }
}
