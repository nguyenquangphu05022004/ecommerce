package com.example.ecommerce.production.service.sku;

import com.example.ecommerce.production.controller.sku.vo.ProductSkuCreateReqVO;
import com.example.ecommerce.production.controller.sku.vo.ProductSkuUpdateReqVO;
import com.example.ecommerce.production.dal.dataobject.sku.ProductSku;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductSkuService {
    ProductSku createProductSku(ProductSkuCreateReqVO reqVO);
    ProductSku updateProductSku(ProductSkuUpdateReqVO reqVO);
    ProductSku getProductSkuById(Long productSkuId);
    List<ProductSku> getListProductSkuByProductSpuId(Long productSpuId);
    void updateImage(Long productSkuId, MultipartFile file);

}
