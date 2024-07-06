package com.example.ecommerce.service.mapper;

import com.example.ecommerce.domain.FileEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ImageMapper {

    public List<String> getImageUrl(
            String fileDownLoadUrl,
            Collection<? extends FileEntity> images
    ) {
        List<String> urls = new ArrayList<>();
        if(images != null) {
            images.forEach(image -> urls.add(fileDownLoadUrl + "/" + image.getName()));
        }
        return urls;
    }
    public <T extends FileEntity> String getImageUrl(
            String fileDownLoadUrl,
            T image
    ) {
        String url = "";
        if(image != null) {
            url = fileDownLoadUrl + "/" + image.getName();
        }
        return url;
    }
}
