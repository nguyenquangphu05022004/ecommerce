package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.Image;
import com.example.ecommerce.repository.ImageRepository;
import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {
    private final ImageRepository imageRepository;
    private final IFilesStorageService filesStorageService;


    @Override
    public Image uploadFile(MultipartFile multipartFile, String shortUrl) {
        Image image = Image.builder()
                .name(multipartFile.getOriginalFilename())
                .shortUrl(shortUrl)
                .build();
        image =  imageRepository.save(image);
        filesStorageService.saveFile(multipartFile);
        return image;
    }

    @Override
    public void deleteFile(String nameFile, Long idImage) {
        imageRepository.deleteById(idImage);
        filesStorageService.deleteFile(nameFile);
    }

    @Override
    public Optional<Image> loadByFileName(String fileName) {
        return imageRepository.findByName(fileName);
    }

}
