package com.example.ecommerce.production.service.brand;

import com.example.ecommerce.production.controller.brand.vo.ProductBrandCreateReqVO;
import com.example.ecommerce.production.controller.brand.vo.ProductBrandUpdateReqVO;
import com.example.ecommerce.production.dal.dataobject.brand.ProductBrand;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductBrandService {
    ProductBrand createProductBrand(ProductBrandCreateReqVO reqVO);
    ProductBrand updateProductBrand(ProductBrandUpdateReqVO reqVO);
    ProductBrand getProductBrandById(Long id);
    List<ProductBrand> getListProductBrand();
    void updateAvatar(Long productBrandId, MultipartFile file);
}
