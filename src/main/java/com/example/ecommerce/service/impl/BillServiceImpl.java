package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.dto.BillDto;
import com.example.ecommerce.entity.Bill;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.repository.BillRepository;
import com.example.ecommerce.service.IBillService;
import com.example.ecommerce.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.print.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.print.Printable.NO_SUCH_PAGE;

@Service
public class BillServiceImpl implements IBillService {

    private final BillRepository billRepository;

    @Autowired
    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<BillDto> records() {
        return billRepository
                .findAllByOrderProductVendorUserUsername(SecurityUtils.username())
                .stream()
                .map(bill -> (BillDto) Convert.BILL.toDto(bill))
                .collect(Collectors.toList());
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
        return null;
    }

    @Override
    public List<BillDto> getBillsByStatus(Status status) {
        List<Bill> bills = billRepository.findAllByStatus(status);
        return bills.stream()
                .map(e -> (BillDto) Convert.BILL.toDto(e))
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
