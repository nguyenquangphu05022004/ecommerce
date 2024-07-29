package com.example.ecommerce.service.mapper;

import com.example.ecommerce.controller.FileController;
import com.example.ecommerce.domain.entities.file.FileEntity;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface  ImageMapper {


    default  List<String> getImageUrl(
            String fileDownLoadUrl,
            Collection<? extends FileEntity> images
    ) {
        List<String> urls = new ArrayList<>();
        if(images != null) {

            images.forEach(image -> {
                String url = MvcUriComponentsBuilder.fromMethodName(
                        FileController.class,
                        "loadFile",
                        fileDownLoadUrl,
                        image.getName()
                ).toUriString();
                urls.add(url);
            });
        }
        return urls;
    }
    default  <T extends FileEntity> String getImageUrl(
            String fileDownLoadUrl,
            T image
    ) {
        String url = "";
        if(image != null) {
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
