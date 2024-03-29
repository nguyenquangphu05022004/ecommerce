package com.example.ecommerce.utils;

import com.example.ecommerce.service.IFilesStorageService;
import com.example.ecommerce.service.impl.FilesStorageServiceImpl;

import java.util.Random;

public class SystemUtils {
    public static int totalPage = 0;
    public static final Integer NUMBER_OF_ITEM = 9;
    public static final String FOLDER_AVATAR = "avatar";
    public static final String FOLDER_PRODUCT_IMAGE = "image-product";
    public static final String FOLDER_EVALUATION_IMAGE = "image-evaluation";
    public static final String SHORT_URL_AVATAR = "files/avatar";
    public static final String SHOR_URL_PRODUCT = "files/image-product";
    public static final IFilesStorageService FILES_STORAGE_SERVICE = new FilesStorageServiceImpl();
    public static String code() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 12; i++) {
            int rd = new Random().nextInt(10);
            builder.append(rd);
        }
        return builder.toString();
    }
}
