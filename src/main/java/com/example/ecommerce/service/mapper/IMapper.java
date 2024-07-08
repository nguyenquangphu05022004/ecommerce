package com.example.ecommerce.service.mapper;

import java.util.List;

public interface IMapper <Entity, Request, Dto>{
    default Entity toEntity(Request request) {return null;}
    Dto toDto(Entity entity);
    default List<Dto> toDtoList(List<Entity> entities) {return null;}
    default Entity toEntity(Request request, Entity entity) {return null;}
}
