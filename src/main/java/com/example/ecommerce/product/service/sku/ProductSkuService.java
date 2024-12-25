package com.example.ecommerce.product.service.sku;

import com.example.ecommerce.product.controller.admin.sku.vo.*;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductSkuService {
    ProductSku createProductSku(ProductSkuCreateReqVO reqVO);
    ProductSku updateProductSku(ProductSkuUpdateReqVO reqVO);
    ProductSku getProductSkuById(Long productSkuId);
    List<ProductSku> getListProductSkuByProductSpuId(Long productSpuId);
    void updateImage(Long productSkuId, MultipartFile file);

    void updateProductSkuStock(ProductSkuUpdateStockReqVO reqVO);

    ProductSkuSimpleRespVO getProductSkuByProperty(ProductSkuSearchReqVO searchReqVO);

}
