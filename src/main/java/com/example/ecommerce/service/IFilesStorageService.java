package com.example.ecommerce.service;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.FileEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface IFilesStorageService {
    FileEntity saveFile(MultipartFile file, EntityType entityType);
    Resource loadFileAsResource(String url);
    void deleteImage(Long fileId);
    void deleteImage(Collection<FileEntity> fileEntities);
}
