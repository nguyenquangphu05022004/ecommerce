package com.example.ecommerce.service;

import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Stock;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl);
    void deleteFile(String nameFile, String folderFile, Long idImage);
    Image loadByFileName(String fileName);
}
