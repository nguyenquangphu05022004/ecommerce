package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.collection.StreamUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.common.pojo.PagingLimitation;
import com.example.ecommerce.frame.common.validate.user.UserUtils;
import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandResVO;
import com.example.ecommerce.product.controller.admin.category.vo.ProductCategoryResVO;
import com.example.ecommerce.product.controller.admin.sku.vo.ProductSkuTradeResVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductDetailsRespVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuCreateReqVO;
import com.example.ecommerce.product.controller.admin.spu.vo.PageProductSpuReqVO;
import com.example.ecommerce.product.controller.admin.spu.vo.ProductSpuUpdateBaseReqVO;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentRepository;
import com.example.ecommerce.product.dal.repository.favorite.ProductFavoriteRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.product.service.search.ProductSearchFactory;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.system.service.user.UserMemberService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.product.constants.ProductionErrorConstant.PRODUCT_SPU_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductSpuServiceImpl implements ProductSpuService{
    private final ProductSpuRepository productSpuRepository;
    private final SellerRepository sellerRepository;
    private final ProductCommentRepository commentRepository;
    private final ProductFavoriteRepository productFavoriteRepository;
    private final UserMemberService userMemberService;

    @Override
    public ProductSpu createProductSpu(ProductSpuCreateReqVO reqVO) {
        UserMember userMember = userMemberService.getUserMemberById(reqVO.getUserId());
        UserUtils.isSeller(userMember);
        ProductSpu productSpu = ProductSpu.builder()
                .productCategory(ProductCategory.builder().id(reqVO.getProductCategoryId()).build())
                .productBrand(ProductBrand.builder().id(reqVO.getProductBrandId()).build())
                .maxPrice(reqVO.getMaxPrice()).minPrice(reqVO.getMinPrice()).name(reqVO.getName())
                .enable(false).description(reqVO.getDescription()).seller((Seller) userMember)
                .build();
        this.productSpuRepository.save(productSpu);
        return productSpu;
    }

    @Override
    public ProductSpu updateProductSpu(ProductSpuUpdateBaseReqVO reqVO) {
        return null;
    }

    @Override
    public PageResult<ProductSpu> getPageProductSpu(PageProductSpuReqVO reqVO) {
        Specification<ProductSpu> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
          if(!MapUtils.isEmpty(reqVO.getCondition())) {
              reqVO.getCondition().entrySet().forEach(entry -> {
                  predicates.add(ProductSearchFactory.getInstance(entry.getKey()).search(root, criteriaBuilder, entry.getValue()));
              });
          }
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
    public PageResult<ProductSpu> getPageProductSpuByUserId(Long userMemberId, int page) {
        Page<ProductSpu> pageResult = this.productSpuRepository.findAllBySellerId(
                userMemberId,
                PageRequest.of(page - 1, PagingLimitation.PRODUCT_SPU_LIMIT));
        return new PageResult<>(pageResult);
    }

    @Override
    public ProductSpu getProductSpuById(Long productSpuId) {
        return this.productSpuRepository.findById(productSpuId)
                .orElseThrow(() -> exception(PRODUCT_SPU_NOT_FOUND));
    }

    @Override
    public ProductDetailsRespVO getDetailsProduct(Long productSpuId) {
        ProductSpu productSpu = this.getProductSpuById(productSpuId);
        List<ProductComment> comments = this.commentRepository.findAllByProductSpuId(productSpuId);
        List<ProductFavorite> productFavorites = productFavoriteRepository.findAllByProductSpuId(productSpuId);

        return ProductDetailsRespVO.builder()
                .id(productSpuId).description(productSpu.getDescription())
                .name(productSpu.getName()).maxPrice(productSpu.getMaxPrice())
                .minPrice(productSpu.getMinPrice()).numComment(CollUtils.size(comments))
                .productCategory(new ProductCategoryResVO(productSpu.getProductCategory()))
                .productBrand(new ProductBrandResVO(productSpu.getProductBrand()))
                .sliders(CollUtils.convertSet(productSpu.getProductSkus(), sku -> sku.getImage()))
                .availableStock(StreamUtils.mapInt(productSpu.getProductSkus(), sku -> sku.getQuantity()).sum())
                .avgRating(StreamUtils.mapDouble(comments, c -> c.getRating()).average().getAsDouble())
                .numFavorite(CollUtils.size(productFavorites)).numSold(0)
                .properties(MapUtils.convertMap(ProductSkuTradeResVO.mapProperties(productSpu.getProductSkus()), k -> k.getId() + "_" + k.getName() ))
                .build();
    }
}

