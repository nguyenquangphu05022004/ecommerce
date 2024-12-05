package com.example.ecommerce.product.service.brand;

import com.example.ecommerce.product.controller.brand.vo.ProductBrandCreateReqVO;
import com.example.ecommerce.product.controller.brand.vo.ProductBrandUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductBrandService {
    ProductBrand createProductBrand(ProductBrandCreateReqVO reqVO);
    ProductBrand updateProductBrand(ProductBrandUpdateReqVO reqVO);
    ProductBrand getProductBrandById(Long id);
    List<ProductBrand> getListProductBrand();
    void updateAvatar(Long productBrandId, MultipartFile file);
}
