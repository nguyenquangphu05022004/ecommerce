package com.example.ecommerce.service.impl;

import com.example.ecommerce.service.IFilesStorageService;
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
public class FilesStorageServiceImpl implements IFilesStorageService {
    private final Path root = Paths.get("src\\main\\resources\\static\\uploads");
    @Override
    public void saveFile(MultipartFile multipartFile, String folder) {
        try {
            Path path = root.resolve(folder);
            Files.copy(multipartFile.getInputStream(), path.resolve(multipartFile.getOriginalFilename()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Resource loadFile(String fileName, String folder) {
        try {
            Path path = root.resolve(folder).resolve(fileName);
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
    public void deleteFile(String fileName, String folder) {
        Path path = root.resolve(folder).resolve(fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
