package com.example.ecommerce.converter;

public interface IGenericConverter<Entity, Dto>{
    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);
    Entity toEntity(Entity entity, Dto dto);
}
