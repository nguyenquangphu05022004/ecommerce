package com.example.ecommerce.service;

import com.example.ecommerce.dto.TrackProductSellerDto;

import java.util.List;

public interface ITrackProductSellerService {
    List<TrackProductSellerDto> getListTop9ByNumberOfSold();
}
