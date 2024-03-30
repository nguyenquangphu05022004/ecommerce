package com.example.ecommerce.service;

import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.entity.Status;

import java.util.List;

public interface IBillService extends IGenericService<BillDto>{
    BillDto saveOrUpdate(BillDto billDto);
    List<BillDto> getBillsByStatus(Status status);
    boolean updateStatus(Long billId);
}
