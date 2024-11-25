package com.example.ecommerce.production.service.category;

import com.example.ecommerce.production.controller.category.vo.ProductCategoryCreateReqVO;
import com.example.ecommerce.production.controller.category.vo.ProductCategoryUpdateReqVO;
import com.example.ecommerce.production.dal.dataobject.category.ProductCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory createProductCategory(ProductCategoryCreateReqVO reqVO);
    ProductCategory updateProductCategory(ProductCategoryUpdateReqVO reqVO);
    List<ProductCategory> getListProductCategory();
    ProductCategory getProductCategoryById(Long id);

    ProductCategory updateProductCategoryThumbnail(Long productCategoryId, MultipartFile fileImage);
}
