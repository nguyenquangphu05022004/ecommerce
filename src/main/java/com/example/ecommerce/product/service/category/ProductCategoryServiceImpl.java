package com.example.ecommerce.product.service.category;

import com.example.ecommerce.file.FileEntity;
import com.example.ecommerce.file.FileStorageService;
import com.example.ecommerce.file.Representation;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryCreateReqVO;
import com.example.ecommerce.product.controller.category.vo.ProductCategoryUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.product.dal.repository.category.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService{
    private final FileStorageService fileStorageService;
    private final ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory createProductCategory(ProductCategoryCreateReqVO reqVO) {
        ProductCategory productCategory = ProductCategory.builder().name(reqVO.getName())
                .categoryParent(reqVO.getCategoryParentId() != null ? ProductCategory.builder().id(reqVO.getCategoryParentId()).build() : null)
                .thumbnail(reqVO.getThumbnail()).build();
        return this.productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategoryUpdateReqVO reqVO) {
        ProductCategory productCategory = this.getProductCategoryById(reqVO.getId()).toBuilder().name(reqVO.getName())
                .categoryParent(reqVO.getCategoryParentId() != null ? ProductCategory.builder().id(reqVO.getCategoryParentId()).build() : null)
                .thumbnail(reqVO.getThumbnail()).build();
        return this.productCategoryRepository.save(productCategory);
    }


    @Override
    public List<ProductCategory> getListProductCategory() {
        return this.productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return this.productCategoryRepository.findById(id)
                .orElseThrow(() -> exception(CATEGORY_NOT_FOUND));
    }

    @Override
    public ProductCategory updateProductCategoryThumbnail(Long productCategoryId, MultipartFile fileImage) {
        ProductCategory productCategory = this.getProductCategoryById(productCategoryId);
        this.fileStorageService.delete(productCategory.getThumbnail());
        FileEntity fileEntity = this.fileStorageService.save(fileImage, Representation.PRODUCT_CATEGORY);
        productCategory = productCategory.toBuilder().thumbnail(fileEntity.getPath()).build();
        return this.productCategoryRepository.save(productCategory);
    }
}
