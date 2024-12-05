package com.example.ecommerce.product.service.category;

import com.example.ecommerce.product.controller.category.vo.ProductCategoryCreateReqVO;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductCategoryService {
    ProductCategory createProductCategory(ProductCategoryCreateReqVO reqVO);
    ProductCategory updateProductCategory(ProductCategoryUpdateReqVO reqVO);
    List<ProductCategory> getListProductCategory();
    ProductCategory getProductCategoryById(Long id);

    ProductCategory updateProductCategoryThumbnail(Long productCategoryId, MultipartFile fileImage);
}
