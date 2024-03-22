package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.TrackProductSellerDto;
import com.example.ecommerce.entity.TrackProductSeller;
import com.example.ecommerce.repository.TrackProductSellerRepository;
import com.example.ecommerce.service.ITrackProductSellerService;
import com.example.ecommerce.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("trackProductService")
public class TrackProductServiceImpl implements ITrackProductSellerService {
    private final TrackProductSellerRepository trackProductSellerRepository;

    @Autowired
    public TrackProductServiceImpl(TrackProductSellerRepository trackProductSellerRepository) {
        this.trackProductSellerRepository = trackProductSellerRepository;
    }

    @Override
    public List<TrackProductSellerDto> getListTop9ByNumberOfSold() {
        List<TrackProductSeller> trackProductSellers =
                trackProductSellerRepository
                        .findAllBySelectTop9ByNumberOfProductsSoldDESQuC();
        List<TrackProductSellerDto> results =
                trackProductSellers
                        .stream()
                        .map(trackEntity -> {
                            return TrackProductSellerDto
                                    .builder()
                                    .id(trackEntity.getId())
                                    .product((ProductDto) Convert.PRO.
                                            toDto(trackEntity.getProduct()))
                                    .numberOfProductsSold(trackEntity.
                                            getNumberOfProductsSold())
                                    .build();
                        }).collect(Collectors.toList());
        return results;
    }
}
