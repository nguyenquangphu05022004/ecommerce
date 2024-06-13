package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.dto.product.DecorationResponse;
import com.example.ecommerce.domain.Decoration;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.DecorationRepository;
import com.example.ecommerce.service.IDecorationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DecorationServiceImpl implements IDecorationService {
    private final ModelMapper mapper;
    private final DecorationRepository decorationRepository;
    @Override
    public void save(DecorationResponse request) {
        Decoration decoration = mapper.map(request, Decoration.class);
        decorationRepository.save(decoration);
    }

    @Override
    public void update(Long id, DecorationResponse request) {
        Decoration decoration = decorationRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("ProductType", id + "")
                );
        decoration = decoration.toBuilder()
                .size(request.getSize().isEmpty() || request.getSize().isBlank()
                        ? decoration.getSize() : request.getSize())
                .color(request.getColor().isEmpty() || request.getColor().isBlank()
                        ? decoration.getColor() : request.getColor())
                .build();
        decorationRepository.save(decoration);
    }

    @Override
    public void delete(Long id) {
        decorationRepository.deleteById(id);
    }

    @Override
    public List<DecorationResponse> listDecoration() {
        return decorationRepository
                .findAll()
                .stream()
                .map(
                        decoration -> mapper.map(decoration, DecorationResponse.class)
                )
                .collect(Collectors.toList());
    }
}
