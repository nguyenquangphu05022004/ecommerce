package com.example.ecommerce.product.service.spu;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.common.json.JsonUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.controller.spu.self.vo.ProductSpuSearchReqVO;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpuDetail;
import com.example.ecommerce.product.dal.repository.brand.ProductBrandRepository;
import com.example.ecommerce.product.dal.repository.category.ProductCategoryRepository;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyValueRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuDetailRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.product.service.search.origin.OriginDataSearch;
import com.example.ecommerce.product.service.search.price.PriceDataSearchVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductSpuServiceImplTest extends TestBase {

    @Autowired private ProductSpuRepository productSpuRepository;
    @Autowired private ProductSkuRepository productSkuRepository;
    @Autowired private ProductCommentRepository productCommentRepository;
    @Autowired private ProductBrandRepository productBrandRepository;
    @Autowired private ProductCategoryRepository productCategoryRepository;
    @Autowired private ProductPropertyRepository productPropertyRepository;
    @Autowired private ProductPropertyValueRepository productPropertyValueRepository;
    @Autowired private ProductSpuService productSpuService;
    @Autowired private ProductSpuDetailRepository productSpuDetailRepository;
    @Test
    void testSearchProductCombination_keyword_cate_brand_price_avgRating_origin_success() {
        ProductCategory c = random3();
        ProductBrand b = random2();
        ProductSpu spu = random1(c, b);
        ProductSku sku = random0(spu);
        ProductComment comment = random4(spu);
        ProductProperty productProperty = randomProperty();
        ProductPropertyValue productPropertyValue = randomPropertyValue(productProperty);
        ProductSpuDetail detail = random5(spu, productProperty, productPropertyValue);
        PriceDataSearchVO priceDataSearchVO = RandomUtils.randomPojo(PriceDataSearchVO.class, s -> {
            s.setFromPrice(450_000);
            s.setToPrice(700_000);
        });
        OriginDataSearch originDataSearch = RandomUtils.randomPojo(OriginDataSearch.class, s-> {
            s.setPropertyId(productProperty.getId());
            s.setPropertyValueId(productPropertyValue.getId());
        });
        ProductSpuSearchReqVO searchReqVO = RandomUtils.randomPojo(ProductSpuSearchReqVO.class, s -> {
            s.setPage(1); s.setSort(false);
            Map<String, String> map = new HashMap<>();
            map.put("category", c.getId().toString());
            map.put("brand", b.getId().toString());
            map.put("keyword", "search product");
//            map.put("price", JsonUtils.write(priceDataSearchVO));
            map.put("rating", "4.1");
            map.put("origin", JsonUtils.write(originDataSearch));
            s.setMap(map);
        });

        PageResult<ProductSpu> productSpuPageResult = this.productSpuService.searchProduct(searchReqVO);

        Assertions.assertEquals(productSpuPageResult.getList().size(), 1);

    }

    public ProductSku random0(ProductSpu spu) {
        ProductSku productSku = RandomUtils.randomPojo(ProductSku.class, p -> {
            p.setProductSkuProperties(null);p.setProductSpu(spu);
            p.setId(null);p.setPrice(500_000);
        });
        this.productSkuRepository.save(productSku);
        return productSku;
    }
    public ProductSpu random1(ProductCategory c1, ProductBrand c2) {
        ProductSpu productSku = RandomUtils.randomPojo(ProductSpu.class, p -> {
           p.setProductCategory(c1); p.setProductBrand(c2); p.setProductSkus(null);
           p.setSeller(null); p.setName("test search product"); p.setProductSpuDetails(null);
           p.setId(null);
        });
        this.productSpuRepository.save(productSku);
        return productSku;
    }
    public ProductBrand random2() {
        ProductBrand productBrand = RandomUtils.randomPojo(ProductBrand.class, s -> {
            s.setId(null);
        });
        this.productBrandRepository.save(productBrand);
        return productBrand;
    }
    public ProductCategory random3() {
        ProductCategory c = RandomUtils.randomPojo(ProductCategory.class, s -> {
            s.setId(null); s.setChildren(null); s.setCategoryParent(null);
        });
        this.productCategoryRepository.save(c);
        return c;
    }
    public ProductComment random4(ProductSpu spu) {
        ProductComment p = new ProductComment();
        p.setProductSpu(spu); p.setRating(4.5);
        this.productCommentRepository.save(p);
        return p;
    }
    public ProductProperty randomProperty() {
        return this.productPropertyRepository.save(RandomUtils.randomPojo(ProductProperty.class, s -> s.setId(null)));
    }
    public ProductPropertyValue randomPropertyValue(ProductProperty p1) {
        return this.productPropertyValueRepository.save(RandomUtils.randomPojo(ProductPropertyValue.class, s -> {
            s.setId(null); s.setProductProperty(p1);
        }));
    }
    public ProductSpuDetail random5(ProductSpu spu, ProductProperty p1, ProductPropertyValue v) {
        ProductSpuDetail detail = ProductSpuDetail.builder().productSpu(spu).productProperty(p1).productPropertyValue(v).build();
        return this.productSpuDetailRepository.save(detail);
    }
}
