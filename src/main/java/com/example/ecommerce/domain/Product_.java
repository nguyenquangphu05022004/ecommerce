package com.example.ecommerce.domain;

import com.example.ecommerce.domain.Language;
import com.example.ecommerce.domain.Product;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Language.class)
public class Product_ {
    public static volatile SingularAttribute<Product, Language> language;

    public static final String LANGUAGE = "language";

}
