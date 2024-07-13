package com.example.ecommerce.service.response;

import com.example.ecommerce.service.dto.VendorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class VendorCartResponse extends VendorDto  {
    private List<ItemResponse> itemResponses;
    public VendorCartResponse() {
        itemResponses = new ArrayList<>();
    }
}
