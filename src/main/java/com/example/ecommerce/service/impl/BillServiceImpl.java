package com.example.ecommerce.service.impl;

import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.entity.Bill;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.repository.BillRepository;
import com.example.ecommerce.service.IBillService;
import com.example.ecommerce.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements IGenericService<BillDto>, IBillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<BillDto> records() {
        return GenericService.records(billRepository, Convert.BILL);
    }

    @Override
    public void delete(Long id) {
        billRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return billRepository.count();
    }

    @Override
    public BillDto findById(Long id) {
        return (BillDto) Convert.BILL.toDto(
                GenericService.findById(billRepository, id)
        );
    }

    @Override
    public BillDto saveOrUpdate(BillDto billDto) {
        Bill bill = null;
        if(billDto.getId() != null) {
            bill = (Bill) Convert.BILL.toEntity(
                    billRepository.findById(billDto.getId()).get(),
                    billDto
            );
        } else {
            bill = (Bill) Convert.BILL.toEntity(billDto);
        }
        return (BillDto) Convert.BILL.toDto(billRepository.save(bill));
    }

    @Override
    public List<BillDto> getBillsByStatus(Status status) {
        List<Bill> bills = billRepository.findAllByStatus(status);
        return bills.stream()
                .map(e -> (BillDto)Convert.BILL.toDto(e))
                .collect(Collectors.toList());
    }
}
