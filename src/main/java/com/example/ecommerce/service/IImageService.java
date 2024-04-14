package com.example.ecommerce.service;

import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Product;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl);
    void deleteFile(String nameFile, String folderFile, Long idImage);
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl, Product product);
    Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl, Evaluation evaluation);

}
