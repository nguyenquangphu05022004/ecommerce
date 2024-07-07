package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.StockDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class StockRequest extends StockDto {
    private Long colorId;
    private List<MultipartFile> fileImages;
}
