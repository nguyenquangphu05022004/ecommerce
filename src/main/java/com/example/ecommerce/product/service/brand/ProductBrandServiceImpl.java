package com.example.ecommerce.product.service.brand;

import com.example.ecommerce.file.FileEntity;
import com.example.ecommerce.file.FileStorageService;
import com.example.ecommerce.file.Representation;
import com.example.ecommerce.frame.common.exception.ServiceException;
import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.product.dal.repository.brand.ProductBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.BRAND_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductBrandServiceImpl implements ProductBrandService{
    private final ProductBrandRepository productBrandRepository;
    private final FileStorageService fileStorageService;
    @Override
    public ProductBrand createProductBrand(ProductBrandCreateReqVO reqVO) {
        ProductBrand productBrand = ProductBrand.builder()
                .slug(reqVO.getSlug()).name(reqVO.getName()).avatar(reqVO.getThumbnail())
                .build();
        return this.productBrandRepository.save(productBrand);
    }

    @Override
    public ProductBrand updateProductBrand(ProductBrandUpdateReqVO reqVO) {
        ProductBrand productBrand = this.getProductBrandById(reqVO.getId())
                .toBuilder().name(reqVO.getName()).slug(reqVO.getSlug())
                .avatar(reqVO.getThumbnail()).build();
        this.productBrandRepository.save(productBrand);
        return productBrand;
    }

    @Override
    public ProductBrand getProductBrandById(Long id) {
        return this.productBrandRepository.findById(id)
                .orElseThrow(() -> exception(BRAND_NOT_FOUND));
    }

    @Override
    public List<ProductBrand> getListProductBrand() {
        return this.productBrandRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateAvatar(Long productBrandId, MultipartFile file) {
        ProductBrand productBrand = this.getProductBrandById(productBrandId);
        this.fileStorageService.delete(productBrand.getAvatar());
        FileEntity fileEntity = fileStorageService.save(file, Representation.PRODUCT_BRAND);
        productBrand = productBrand.toBuilder().avatar(fileEntity.getPath()).build();
        this.productBrandRepository.save(productBrand);
    }

}
