package com.example.ecommerce.service;

import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl);
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl, Product product);
    void deleteFile(Long id, String nameImage, String folder);
}
