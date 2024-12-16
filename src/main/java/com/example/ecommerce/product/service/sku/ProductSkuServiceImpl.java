package com.example.ecommerce.product.service.sku;

import com.example.ecommerce.file.FileEntity;
import com.example.ecommerce.file.FileStorageService;
import com.example.ecommerce.file.Representation;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.notification.NotificationEventManager;
import com.example.ecommerce.product.controller.sku.vo.*;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyValueRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuPropertyRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.notification.NotificationEvent.ON_STOCK;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PRODUCT_SKU_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductSkuServiceImpl implements ProductSkuService{
    /**
     * Repository
     */
    private final ProductSkuRepository productSkuRepository;
    private final ProductSkuPropertyRepository productSkuPropertyRepository;
    private final ProductPropertyRepository productPropertyRepository;
    private final ProductPropertyValueRepository productPropertyValueRepository;
    private final NotificationEventManager notificationEventManager;
    /**
     * Notify users, when stock is updated(only current stock == 0)
     */
//    private final ProductStockObservable productStockObservable;

    /**
     * Service
     */
    private final FileStorageService fileStorageService;
    @Override
    public ProductSku createProductSku(ProductSkuCreateReqVO reqVO) {
        ProductSku productSku = ProductSku.builder()
                .productSpu(ProductSpu.builder().id(reqVO.getProductSpuId()).build())
                .image(reqVO.getImage()).price(reqVO.getPrice()).quantity(reqVO.getQuantity())
                .build();
       return this.productSkuRepository.save(productSku);
    }

    @Override
    public ProductSku updateProductSku(ProductSkuUpdateReqVO reqVO) {
        ProductSku productSku = getProductSkuById(reqVO.getProductSpuId()).toBuilder()
                .productSpu(ProductSpu.builder().id(reqVO.getProductSpuId()).build())
                .price(reqVO.getPrice()).quantity(reqVO.getQuantity()).image(reqVO.getImage()).build();
        this.productSkuRepository.save(productSku);
        return productSku;
    }


    @Override
    public ProductSku getProductSkuById(Long productSkuId) {
        return this.productSkuRepository.findById(productSkuId)
                .orElseThrow(() ->exception(PRODUCT_SKU_NOT_FOUND));
    }

    @Override
    public List<ProductSku> getListProductSkuByProductSpuId(Long productSpuId) {
        return this.productSkuRepository.findAllByProductSpuId(productSpuId);
    }

    @Override
    public void updateImage(Long productSkuId, MultipartFile file) {
        FileEntity fileEntity = this.fileStorageService.save(file, Representation.PRODUCT_SKU);
        ProductSku productSku = getProductSkuById(productSkuId).toBuilder()
                .image(fileEntity.getPath()).build();
        this.productSkuRepository.save(productSku);
    }

    @Override
    public void updateProductSkuStock(ProductSkuUpdateStockReqVO reqVO) {
        ProductSku productSku = getProductSkuById(reqVO.getProductSkuId());
        boolean stockEmpty = false;
        if(productSku.getQuantity() == 0) {
            stockEmpty = true;
        }
        productSku.setQuantity(reqVO.getNewStock());
        this.productSkuRepository.save(productSku);
        if(stockEmpty) {
            notificationEventManager.notify(ON_STOCK, productSku.getId().toString());
        }
    }

    @Override
    public ProductSkuSimpleRespVO getProductSkuByProperty(ProductSkuSearchReqVO searchReqVO) {
        Set<ProductSkuProperty> productSkuProperties = CollUtils.convertSet(searchReqVO.getProperties(), (p1, p2) -> {
            return Set.of(ProductSkuProperty.builder()
                    .productPropertyValue(ProductPropertyValue.builder().id(p2).build())
                    .productProperty(ProductProperty.builder().id(p1).build())
                    .build());
        });
        List<ProductSku> productSkus = this.getListProductSkuByProductSpuId(searchReqVO.getProductSpuId());

        for(ProductSku productSku : productSkus) {
            boolean match = productSku.getProductSkuProperties().containsAll(productSkuProperties);
            if(match) {
                return new ProductSkuSimpleRespVO(productSku);
            }
        }
        return null;
//        ProductSku sku = StreamUtils.filterAndThen(productSkus, proSku -> {
//            return CollUtils.containsAll(proSku.getProductSkuProperties(), productSkuProperties);
//        }).findFirst().orElseThrow(() -> exception(PRODUCT_SKU_NOT_FOUND));

//        return new ProductSkuSimpleRespVO(sku);
    }

}
