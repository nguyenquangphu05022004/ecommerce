package com.example.ecommerce.production.service.sku;

import com.example.ecommerce.file.FileEntity;
import com.example.ecommerce.file.FileStorageService;
import com.example.ecommerce.file.Representation;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuCreateReqVO;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuUpdateReqVO;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuUpdateStockReqVO;
import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.production.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.production.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.production.dal.repository.property.ProductPropertyValueRepository;
import com.example.ecommerce.production.dal.repository.sku.ProductSkuPropertyRepository;
import com.example.ecommerce.production.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.production.service.sku.notify.ProductStockObservable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.production.enums.ProductionErrorConstant.PRODUCT_SKU_NOT_FOUND;

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

    /**
     * Notify users, when stock is updated(only current stock == 0)
     */
    private final ProductStockObservable productStockObservable;

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
    public void updateProductSpuStock(ProductSkuUpdateStockReqVO reqVO) {
        ProductSku productSku = getProductSkuById(reqVO.getProductSkuId());
        if(productSku.getQuantity() == 0) {
            productSku.setQuantity(reqVO.getNewStock());
            this.productSkuRepository.save(productSku);
//            this.productStockObservable.setData();
        }
    }

}
