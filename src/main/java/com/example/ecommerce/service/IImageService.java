package com.example.ecommerce.service;

import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Stock;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IImageService {
    Image uploadFile(MultipartFile multipartFile, String shortUrl);
    void deleteFile(String nameFile, Long idImage);
    Optional<Image> loadByFileName(String fileName);
}
