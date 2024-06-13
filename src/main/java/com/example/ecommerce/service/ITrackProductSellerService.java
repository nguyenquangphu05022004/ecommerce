package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.TrackProductSellerDto;

import java.util.List;

public interface ITrackProductSellerService {
    List<TrackProductSellerDto> getListTopNumberByNumberOfSold( int page, int size);
}
