package com.example.ecommerce.service.mapper;

import java.util.List;

public interface IMapper <Entity, Request, Dto>{
    Entity toEntity(Request request);
    Dto toDto(Entity entity);
    List<Dto> toDtoList(List<Entity> entities);
}
