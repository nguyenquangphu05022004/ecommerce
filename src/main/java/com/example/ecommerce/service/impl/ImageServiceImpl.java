package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Evaluation;
import com.example.ecommerce.entity.Image;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Stock;
import com.example.ecommerce.repository.ImageRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service

public class ImageServiceImpl implements IImageService {
    private final ImageRepository imageRepository;
    private final IFilesStorageService filesStorageService;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, IFilesStorageService filesStorageService) {
        this.imageRepository = imageRepository;
        this.filesStorageService = filesStorageService;
    }

    @Override
    public Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl) {
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .shortUrl(shortUrl)
                .build();
        image =  imageRepository.save(image);
        filesStorageService.saveFile(multipartFile, folder);
        return image.toBuilder().user(null).category(null).build();
    }

    @Override
    public void deleteFile(String nameFile, String folderFile, Long idImage) {
        imageRepository.deleteById(idImage);
        filesStorageService.deleteFile(nameFile, folderFile);
    }

    @Override
    public Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl, Stock stock) {
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .shortUrl(shortUrl)
                .stock(stock)
                .build();
        image =  imageRepository.save(image);
        filesStorageService.saveFile(multipartFile, folder);
        return image.toBuilder().user(null).category(null).stock(null).build();
    }

    @Override
    public Image uploadFile(MultipartFile multipartFile, String folder,
                            String shortUrl, Evaluation evaluation) {
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .shortUrl(shortUrl)
                .evaluation(evaluation)
                .build();
        image =  imageRepository.save(image);
        filesStorageService.saveFile(multipartFile, folder);
        return image.toBuilder().evaluation(null).build();
    }
}
