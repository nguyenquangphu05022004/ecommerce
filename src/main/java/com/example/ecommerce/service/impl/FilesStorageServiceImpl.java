package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.entities.EntityType;
import com.example.ecommerce.domain.entities.FileEntity;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.handler.exception.ResourcesNotFoundException;
import com.example.ecommerce.repository.FileEntityRepository;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements IFilesStorageService {
    private final String PATH_UPLOADS = "uploads";
    private final FileEntityRepository fileEntityRepository;


    @Override
    public FileEntity saveFile(MultipartFile file, EntityType entityType) {
        if(file == null || file.isEmpty()) {
            throw new GeneralException("file can't null");
        }
        String url = PATH_UPLOADS;
        File f ;
        if (!(f = new File(url)).exists() && f.mkdir()) ;
        url = url + "/" + entityType.getEntityType().name();
        if (!(f = new File(url)).exists() && f.mkdir()) ;
        url = url + "/" + entityType.getEntityId();
        if (!(f = new File(url)).exists() && f.mkdir()) ;
        String extension = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        String name = UUID.randomUUID() + "." + extension;
        url += "/" + name;

        FileEntity fileEntity = FileEntity.builder()
                .entityType(entityType)
                .name(name)
                .path(url)
                .build();

        try {
            Files.copy(file.getInputStream(), Path.of(url));
            return fileEntityRepository.save(fileEntity);
        } catch (IOException e) {
            throw new GeneralException("can't save file");
        }
    }

    @Override
    public Resource loadFileAsResource(String url) {
        if (Files.exists(Path.of(url))) {
            try {
                Resource resource = new UrlResource(url);
            } catch (MalformedURLException e) {
                throw new GeneralException("can't get image");
            }
        }
        return null;
    }

    @Override
    public void deleteImage(Long fileId) {
        FileEntity file = fileEntityRepository.findById(fileId)
                .orElseThrow(() -> new ResourcesNotFoundException("file not found"));
        Path path = Path.of(file.getPath());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new GeneralException("Can't delete file");
        }
    }

    @Override
    public void deleteImage(Collection<FileEntity> fileEntities) {
        if(!CollectionUtils.isEmpty(fileEntities)) {
            fileEntities.forEach(s -> {
                deleteImage(s.getId());
            });
        }
    }
}
