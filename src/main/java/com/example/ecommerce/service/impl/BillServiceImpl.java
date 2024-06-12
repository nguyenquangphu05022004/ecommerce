package com.example.ecommerce.service.impl;

import com.example.ecommerce.converter.BillConverterImpl;
import com.example.ecommerce.domain.dto.product.BillDto;
import com.example.ecommerce.domain.Bill;
import com.example.ecommerce.domain.dto.ENUM.Status;
import com.example.ecommerce.repository.BillRepository;
import com.example.ecommerce.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements IBillService {

    private final BillRepository billRepository;
    private final BillConverterImpl billConverter;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, BillConverterImpl billConverter) {
        this.billRepository = billRepository;
        this.billConverter = billConverter;
    }

    @Override
    public List<BillDto> records() {
        return null;
//        return billRepository
//                .findAllByOrderProductVendorUserUsername(SecurityUtils.username())
//                .stream()
//                .map(bill -> billConverter.toDto(bill))
//                .collect(Collectors.toList());
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
        return billConverter.toDto(billRepository.findById(id).get());
    }

    @Override
    public BillDto saveOrUpdate(BillDto billDto) {
        return null;
    }

    @Override
    public List<BillDto> getBillsByStatus(Status status) {
        List<Bill> bills = billRepository.findAllByStatus(status);
        return bills.stream()
                .map(e ->billConverter.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateStatus(Long billId) {
        Bill bill = billRepository.findById(billId).get();
        bill.setStatus(Status.SUCCESS);
        billRepository.save(bill);
        return true;
    }
}
