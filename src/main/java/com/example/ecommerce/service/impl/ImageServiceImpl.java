package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Image;
import com.example.ecommerce.repository.ImageRepository;
import com.example.ecommerce.service.IImageService;
import com.example.ecommerce.utils.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements IImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl) {
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .shortUrl(shortUrl)
                .build();
        image =  imageRepository.save(image);
        SystemUtils.FILES_STORAGE_SERVICE.saveFile(multipartFile, folder);
        return image.toBuilder().user(null).category(null).build();
    }


    @Override
    public void deleteFile(Long id, String nameImage, String folder) {
        imageRepository.deleteById(id);
        SystemUtils.FILES_STORAGE_SERVICE.deleteFile(nameImage, folder);
    }
}
