package com.example.ecommerce.service;

import com.example.ecommerce.controller.FileController;
import com.example.ecommerce.domain.entities.FileEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.*;

public interface ImageMapper {


    default List<String> getImageUrl(
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
                            image.getName()
                    ).toUriString();
                }).toList();
    }

    default <T extends FileEntity> String getImageUrl(T image) {
        String url = "";
        if (image != null) {
            url = MvcUriComponentsBuilder.fromMethodName(
                    FileController.class,
                    "loadFile",
                    image.getName()
            ).toUriString();
        }
        return url;
    }
}
