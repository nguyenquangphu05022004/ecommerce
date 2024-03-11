package com.example.ecommerce.service;

import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.entity.Status;

import java.util.List;

public interface IBillService {
    BillDto saveOrUpdate(BillDto billDto);
    List<BillDto> getBillsByStatus(Status status);
}
