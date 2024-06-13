package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.dto.utilize.Resources;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements IFilesStorageService {
    private final Resources resources;
    private  Path root = null;

    @Override
    public void saveFile(MultipartFile multipartFile) {
        if(root == null) {
            root = Paths.get(resources.getUrlFilesStorage());
        }
        try {
            Files.copy(multipartFile.getInputStream(), root.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Resource loadFile(String fileName) {
        if(root == null) {
            root = Paths.get(resources.getUrlFilesStorage());
        }
        try {
            Path path = root.resolve(fileName);
            Resource resource = new UrlResource(path.toUri());
            if(resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Couldn't read file with name: " + fileName);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        if(root == null) {
            root = Paths.get(resources.getUrlFilesStorage());
        }
        Path path = root.resolve(fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
