package com.example.ecommerce.service.mapper;

import com.example.ecommerce.controller.FileController;
import com.example.ecommerce.domain.entities.file.FileEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.*;

public interface ImageMapper {


    default List<String> getImageUrl(
            String fileDownLoadUrl,
            Collection<? extends FileEntity> images
    ) {
        if(CollectionUtils.isEmpty(images)) {
            return Collections.emptyList();
        }
        return images.stream()
                .map(image -> {
                    return MvcUriComponentsBuilder.fromMethodName(
                            FileController.class,
                            "loadFile",
                            fileDownLoadUrl,
                            image.getName()
                    ).toUriString();
                }).toList();
    }

    default <T extends FileEntity> String getImageUrl(
            String fileDownLoadUrl,
            T image
    ) {
        String url = "";
        if (image != null) {
            url = MvcUriComponentsBuilder.fromMethodName(
                    FileController.class,
                    "loadFile",
                    fileDownLoadUrl,
                    image.getName()
            ).toUriString();
        }
        return url;
    }
}
