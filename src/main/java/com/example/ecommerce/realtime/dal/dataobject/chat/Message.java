package com.example.ecommerce.realtime.dal.dataobject.chat;

import com.example.ecommerce.file.FileEntity;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.trade.dal.dataobject.order.Order;

import java.util.Set;

public class Message {
    private String content;
    private Set<FileEntity> fileEntities;
    private ProductSpu productSpu;
}
