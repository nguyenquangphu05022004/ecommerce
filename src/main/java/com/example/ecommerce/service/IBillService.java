package com.example.ecommerce.service;

import com.example.ecommerce.domain.dto.product.BillDto;
import com.example.ecommerce.domain.dto.ENUM.Status;

import java.util.List;

public interface IBillService extends IGenericService<BillDto>{
    BillDto saveOrUpdate(BillDto billDto);
    List<BillDto> getBillsByStatus(Status status);
    boolean updateStatus(Long billId);
}
