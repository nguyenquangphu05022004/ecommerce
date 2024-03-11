package com.example.ecommerce.service.impl;

import com.example.ecommerce.converter.IGenericConverter;
import com.example.ecommerce.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenericService {

    protected static <Entity, Dto> List<Dto> records(
            JpaRepository repository,
            IGenericConverter converter
    ) {
        List<Entity> list = repository.findAll();
        return list.stream()
                .map(entity -> (Dto)converter.toDto(entity))
                .collect(Collectors.toList());
    }

    protected static <Entity> Entity findById(
            JpaRepository repository,
            Long id
    ) {
        Optional<Entity> entity = repository.findById(id);
        return entity.orElseThrow(
                () -> new NotFoundException("Id", String.valueOf(id))
        );
    }


}
