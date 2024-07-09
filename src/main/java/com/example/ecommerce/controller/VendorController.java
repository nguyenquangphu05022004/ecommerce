package com.example.ecommerce.controller;


import com.example.ecommerce.service.IVendorService;
import com.example.ecommerce.service.request.VendorRequest;
import com.example.ecommerce.service.response.OperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/vendors")
public class VendorController {
    private final IVendorService vendorService;

    @PostMapping
    public ResponseEntity<?> createVendor(@RequestBody VendorRequest request) {
        vendorService.saveOrUpdate(request);
        return new ResponseEntity<>(
                new OperationResponse(true, String.format("You created vendor for user: %s", request.getUsername())),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/follow/{vendorId}")
    public ResponseEntity<?> userFollowVendor(
            @PathVariable("vendorId") Long vendorId
    ) {
        vendorService.userFollow(vendorId);
        return new ResponseEntity<>(
                new OperationResponse(true, String.format("You followed vendor with id: %s", vendorId)),
                HttpStatus.OK
        );
    }
}
