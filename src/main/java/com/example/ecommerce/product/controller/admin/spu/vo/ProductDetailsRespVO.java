package com.example.ecommerce.product.controller.admin.spu.vo;

import com.example.ecommerce.product.controller.admin.brand.vo.ProductBrandResVO;
import com.example.ecommerce.product.controller.admin.category.vo.ProductCategoryResVO;
import com.example.ecommerce.product.controller.admin.property.vo.ProductPropertyValueResVO;
import com.example.ecommerce.product.controller.admin.spu.vo.info.ProductSpuInfoRespVO;
import com.example.ecommerce.system.controller.admin.user.vo.SellerDetailsRespVO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Builder
@Getter
public class ProductDetailsRespVO {
    private Long id;
    private String name;
    private Set<String> sliders;
    private Integer maxPrice;
    private Integer minPrice;
    private Integer availableStock;
    private Integer numSold;
    private Integer numComment;
    private Integer numFavorite;
    private Double avgRating;
    private String description;
    private SellerDetailsRespVO seller;
    private List<ProductSpuInfoRespVO> spuInfos;
    //key: id_name
    private Map<String, Set<ProductPropertyValueResVO>> properties;
//    private Map<ProductPropertyVO, Set<ProductPropertyValueResVO>> properties;
    private ProductBrandResVO productBrand;
    private ProductCategoryResVO productCategory;
}
