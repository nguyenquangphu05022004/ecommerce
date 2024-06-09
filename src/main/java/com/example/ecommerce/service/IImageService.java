package com.example.ecommerce.service;

import com.example.ecommerce.domain.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface IImageService {
    Image uploadFile(MultipartFile multipartFile, String shortUrl);
    void deleteFile(String nameFile, Long idImage);
    Optional<Image> loadByFileName(String fileName);
}
