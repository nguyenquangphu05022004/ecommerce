package com.example.ecommerce.service.impl;

import com.example.ecommerce.domain.*;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.CategoryImageRepository;
import com.example.ecommerce.repository.EvaluationImageRepository;
import com.example.ecommerce.repository.StockImageRepository;
import com.example.ecommerce.repository.UserImageRepository;
import com.example.ecommerce.service.IFilesStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilesStorageServiceImpl implements IFilesStorageService {
    private final String UPLOAD_DIRS = "uploads/";
    private final String FILE_NOT_FOUND = "File not found";
    private final UserImageRepository userImageRepository;
    private final StockImageRepository stockImageRepository;
    private final EvaluationImageRepository evaluationImageRepository;
    private final CategoryImageRepository categoryImageRepository;

    @Override
    public void saveFile(MultipartFile file, Long entityId, EntityType entityType) {
        if (file == null || file.isEmpty()) {
            throw new GeneralException(FILE_NOT_FOUND);
        }
        String extension = getFilePathExtension(file.getOriginalFilename());
        String uploadDir = UPLOAD_DIRS + entityType.getType() + "/";
        try {
            if (!Files.exists(getPath(uploadDir))) {
                Files.createDirectories(getPath(uploadDir));
            }
            uploadDir += entityId + "/";
            if (!Files.exists(getPath(uploadDir))) {
                Files.createDirectories(getPath(uploadDir));
            }
            String fullFileName = String.format("%s.%s", UUID.randomUUID(), extension);
            String path = uploadDir + fullFileName;
            Files.copy(file.getInputStream(), getPath(path), StandardCopyOption.REPLACE_EXISTING);
            switch (entityType) {
                case USER:
                    UserImage userImage = UserImage.builder()
                            .name(fullFileName)
                            .type(entityType.getType())
                            .path(path)
                            .user(User.builder().id(entityId).build())
                            .build();
                    userImageRepository.save(userImage);
                    break;
                case PRODUCT:
                    StockImage stockImage = StockImage.builder()
                            .name(fullFileName)
                            .type(entityType.getType())
                            .path(path)
                            .stock(Stock.builder().id(entityId).build())
                            .build();
                    stockImageRepository.save(stockImage);
                    break;
                case CATEGORY:
                    CategoryImage categoryImage = CategoryImage.builder()
                            .name(fullFileName)
                            .type(entityType.getType())
                            .path(path)
                            .category(Category.builder().id(entityId).build())
                            .build();
                    categoryImageRepository.save(categoryImage);
                    break;
                case EVALUATION:
                    EvaluationImage evaluationImage = EvaluationImage.builder()
                            .name(fullFileName)
                            .type(entityType.getType())
                            .path(path)
                            .evaluation(Evaluation.builder().id(entityId).build())
                            .build();
                    evaluationImageRepository.save(evaluationImage);
                    break;
            }


        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName, EntityType entityType) {
        try {
            String filePath = getPathFile(entityType, fileName);
            Resource resource = new UrlResource(getPath(filePath).toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new GeneralException("File not found: " + fileName);
            }
        } catch (Exception ex) {
            throw new GeneralException(ex.getMessage());
        }
    }


    @Override
    public void deleteImage(FileEntity file) {
        if (file == null) return;
        try {
            Path path = getPath(file.getPath());
            if (!Files.exists(path)) {
                throw new GeneralException("File not found: " + file.getName());
            }
            if (file.getType().equals(EntityType.PRODUCT.getType())) {
                stockImageRepository.delete((StockImage) file);
            } else if (file.getType().equals(EntityType.CATEGORY.getType())) {
                categoryImageRepository.delete((CategoryImage) file);
            } else if (file.getType().equals(EntityType.EVALUATION.getType())) {
                evaluationImageRepository.delete((EvaluationImage) file);
            } else if (file.getType().equals(EntityType.USER.getType())) {
                userImageRepository.delete((UserImage) file);
            }
            Files.delete(path);
        } catch (Exception ex) {
            throw new GeneralException(ex.getMessage());
        }
    }

    @Override
    public void deleteImage(Collection<? extends FileEntity> list) {
        if (list != null) {
            list.forEach(this::deleteImage);
        }
    }


    private Path getPath(String path) {
        return Paths.get(path);
    }

    private String getFilePathExtension(String file) {
        return Optional.ofNullable(file)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(f.lastIndexOf(".") + 1))
                .orElseThrow();
    }

    private String getPathFile(EntityType entityType, String fileName) {
        if (entityType == EntityType.CATEGORY) {
            return categoryImageRepository
                    .findByName(fileName)
                    .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND))
                    .getPath();
        } else if (entityType == EntityType.EVALUATION) {
            return evaluationImageRepository
                    .findByName(fileName)
                    .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND))
                    .getPath();
        } else if (entityType == EntityType.PRODUCT) {
            return stockImageRepository
                    .findByName(fileName)
                    .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND))
                    .getPath();
        } else if (entityType == EntityType.USER) {
            return userImageRepository
                    .findByName(fileName)
                    .orElseThrow(() -> new GeneralException(FILE_NOT_FOUND))
                    .getPath();
        }
        throw new GeneralException(FILE_NOT_FOUND);
    }
}
