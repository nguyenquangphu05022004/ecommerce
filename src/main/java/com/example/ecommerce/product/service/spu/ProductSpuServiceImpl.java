package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.common.pojo.PagingLimitation;
import com.example.ecommerce.frame.common.string.StringUtils;
import com.example.ecommerce.frame.security.core.utils.SecurityUtils;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuSearchReqVO;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.product.service.search.ProductSearchFactory;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PRODUCT_SPU_NOT_FOUND;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.SELLER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductSpuServiceImpl implements ProductSpuService{
    private final ProductSpuRepository productSpuRepository;
    private final SellerRepository sellerRepository;
    @Override
    public ProductSpu createProductSpu(ProductSpuCreateReqVO reqVO) {
        Optional<Seller> opSeller = sellerRepository.findByUserMemberId(SecurityUtils.getLoginUserMemberId());
        if(opSeller.isEmpty()) {
            throw exception(SELLER_NOT_FOUND);
        }
        ProductSpu productSpu = ProductSpu.builder()
                .productCategory(ProductCategory.builder().id(reqVO.getProductCategoryId()).build())
                .productBrand(ProductBrand.builder().id(reqVO.getProductBrandId()).build())
                .maxPrice(reqVO.getMaxPrice()).minPrice(reqVO.getMinPrice()).name(reqVO.getName())
                .enable(false).description(reqVO.getDescription()).seller(opSeller.get())
                .build();
        this.productSpuRepository.save(productSpu);
        return productSpu;
    }

    @Override
    public ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO) {
        return null;
    }

    @Override
    public PageResult<ProductSpu> searchProduct(ProductSpuSearchReqVO reqVO) {
        Specification<ProductSpu> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
          reqVO.getMap().entrySet().forEach(entry -> {
              predicates.add(ProductSearchFactory.getInstance(entry.getKey()).search(root, criteriaBuilder, entry.getValue()));
          });
          Predicate res = null;
          for(Predicate p : predicates) {
              res = (res == null) ? p : criteriaBuilder.and(res, p);
          }
          return res;
        };
        Pageable pageable = PageRequest.of(reqVO.getPage() - 1, PagingLimitation.PRODUCT_SPU_LIMIT);
        Page<ProductSpu> paging = this.productSpuRepository.findAll(spec, pageable);
        List<ProductSpu> productSpus = new ArrayList<>(paging.getContent());
        if(reqVO.isSort()) {

        }
        return new PageResult<>(paging.getNumber(), paging.getTotalPages(), productSpus);
    }


    @Override
    public PageResult<ProductSpu> getListProductSpuBySeller(Long userMemberId, int page) {
        Page<ProductSpu> pageResult = this.productSpuRepository.findAllBySellerUserMemberId(
                userMemberId,
                PageRequest.of(page - 1, PagingLimitation.PRODUCT_SPU_LIMIT));
        return new PageResult<>(pageResult);
    }

    @Override
    public ProductSpu getProductSpuById(Long productSpuId) {
        return this.productSpuRepository.findById(productSpuId)
                .orElseThrow(() -> exception(PRODUCT_SPU_NOT_FOUND));
    }
}
