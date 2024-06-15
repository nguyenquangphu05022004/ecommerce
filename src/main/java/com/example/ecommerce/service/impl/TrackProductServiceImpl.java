package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.TrackProductSeller;
import com.example.ecommerce.domain.dto.product.TrackProductSellerDto;
import com.example.ecommerce.repository.TrackProductSellerRepository;
import com.example.ecommerce.service.ITrackProductSellerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("trackProductService")
@RequiredArgsConstructor
public class TrackProductServiceImpl implements ITrackProductSellerService {
    private final TrackProductSellerRepository trackProductSellerRepository;
    private final ModelMapper mapper;

    @Override
    public List<TrackProductSellerDto> getListTopNumberByNumberOfSold( int page, int size) {
        Pageable paging = PageRequest.of(page - 1, size,
                Sort.by("numberOfProductsSold").descending());
        List<TrackProductSeller> trackProductSellers = trackProductSellerRepository
                .findAll(paging).getContent();
        return trackProductSellers.stream()
                .map(trackEntity -> mapper.map(trackEntity, TrackProductSellerDto.class))
                .collect(Collectors.toList());
    }
}
