package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Image;
import com.example.ecommerce.repository.ImageRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {
    private final ImageRepository imageRepository;
    private final IFilesStorageService filesStorageService;


    @Override
    public Image uploadFile(MultipartFile multipartFile, String folder, String shortUrl) {
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .shortUrl(shortUrl)
                .build();
        image =  imageRepository.save(image);
        filesStorageService.saveFile(multipartFile, folder);
        return image;
    }

    @Override
    public void deleteFile(String nameFile, String folderFile, Long idImage) {
        imageRepository.deleteById(idImage);
        filesStorageService.deleteFile(nameFile, folderFile);
    }

}
