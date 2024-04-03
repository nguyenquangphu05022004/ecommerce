package com.example.ecommerce.service;

import com.example.ecommerce.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl);
    void deleteFile(Long id, String nameImage, String folder);
}
