package com.example.ecommerce.controller;

import com.example.ecommerce.service.IBillService;
import com.example.ecommerce.service.impl.BillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BillController {
    private final IBillService billService;

    @Autowired
    public BillController(IBillService billService) {
        this.billService = billService;
    }

    @GetMapping("/vendor/products/bills")
    public String getAllBillOfVendor(Model model) {
        model.addAttribute("bills", billService.records());
        return "admin/product/product-bills";
    }

    @GetMapping("/bills/product/check-in/{billId}")
    public ResponseEntity<?> scanQRCodeAfterCustomerReceiveProduct(
            @PathVariable("billId") Long billId
    ) {
        billService.updateStatus(billId);
        return ResponseEntity.ok("Check-In successfull");
    }
}
