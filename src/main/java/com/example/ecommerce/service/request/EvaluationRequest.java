package com.example.ecommerce.service.request;

import com.example.ecommerce.service.dto.EvaluationDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@SuperBuilder(toBuilder = true)
public class EvaluationRequest extends EvaluationDto {
    private List<MultipartFile> fileImages;
}
