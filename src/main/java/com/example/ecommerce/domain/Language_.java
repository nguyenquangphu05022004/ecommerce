package com.example.ecommerce.domain;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Language.class)
public class Language_ {
    public static volatile SingularAttribute<Language, String> nameVn;
    public static volatile SingularAttribute<Language, String> nameEn;

    public static final String NAME_VN = "nameVn";
    public static final String NAME_EN = "nameEn";

}
