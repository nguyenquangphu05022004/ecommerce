package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.EvaluationDto;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class EvaluationRequest extends EvaluationDto {
    private List<MultipartFile> fileImages;
}
