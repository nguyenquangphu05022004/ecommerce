package com.example.ecommerce.utils;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.converter.impl.Converter;
import com.example.ecommerce.converter.impl.FactoryConverter;

public class Convert {
    public static final IGenericConverter CATE = FactoryConverter.getInstance(Converter.CATEGORY);
    public static final IGenericConverter EVAL = FactoryConverter.getInstance(Converter.EVALUATION);
    public static final IGenericConverter PRO = FactoryConverter.getInstance(Converter.PRODUCT);
    public static final IGenericConverter VEND = FactoryConverter.getInstance(Converter.VENDOR);
    public static final IGenericConverter USER = FactoryConverter.getInstance(Converter.USER);
    public static final IGenericConverter BASKET = FactoryConverter.getInstance(Converter.BASKET);

}
