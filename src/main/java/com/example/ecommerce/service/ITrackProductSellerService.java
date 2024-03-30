package com.example.ecommerce.service;

import com.example.ecommerce.dto.TrackProductSellerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITrackProductSellerService {
    List<TrackProductSellerDto> getListTopNumberByNumberOfSold( int page, int size);
}
