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
    public static final String FOLDER_CATEGORY_IMAGE = "image-category";
    public static final String SHORT_URL_AVATAR = "files/avatar";
    public static final String SHORT_URL_PRODUCT = "files/image-product";
    public static final String SHORT_URL_CATEGORY="files/image-category";
    public static final IFilesStorageService FILES_STORAGE_SERVICE = new FilesStorageServiceImpl();
    public static String code() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 12; i++) {
            int rd = new Random().nextInt(10);
            builder.append(rd);
        }
        return builder.toString();
    }
    public static String getFormatNumber(Integer value) {
        String number = String.valueOf(value);
        int n = number.length();
        StringBuilder builder = new StringBuilder();
        while(n > 0) {
            StringBuilder rev = new StringBuilder(number.substring(n - 3 >= 0 ? n - 3 : 0, n));
            builder.append(rev.reverse());
            if(n - 3 > 0) builder.append(",");
            n -= 3;
        }
        return builder.reverse().toString();
    }
}
