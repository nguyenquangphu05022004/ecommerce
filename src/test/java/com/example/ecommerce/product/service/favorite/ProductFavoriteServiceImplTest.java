package com.example.ecommerce.product.service.favorite;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.favorite.ProductFavoriteRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import(ProductFavoriteServiceImpl.class)
class ProductFavoriteServiceImplTest extends TestBase {

    @Autowired
    private ProductFavoriteServiceImpl sellerFavoriteService;
    @Autowired
    private ProductSpuRepository productSpuRepository;
    @Autowired
    private UserMemberRepository userMemberRepository;
    @Autowired
    private ProductFavoriteRepository productFavoriteRepository;
    @Test
    void test_createFavorite_success() {
        UserMember u2 = randomUser();
        ProductSpu s1 = randomSpu();
        this.sellerFavoriteService.createFavorite(u2.getId(), s1.getId());
        boolean present = this.productFavoriteRepository.findByUserMemberIdAndProductSpuId(u2.getId(), s1.getId()).isPresent();

        Assertions.assertEquals(present, true);

    }

    @Test
    void test_deleteFavorite_success() {
        UserMember u2 = randomUser();
        ProductSpu s1 = randomSpu();
        randomEn(u2, s1);
        this.sellerFavoriteService.deleteFavorite(u2.getId(), s1.getId());

        boolean present = this.productFavoriteRepository.findByUserMemberIdAndProductSpuId(u2.getId(), s1.getId()).isPresent();
        Assertions.assertEquals(present, false);


    }

    @Test
    void test_userHasFavorite_success() {
        UserMember u2 = randomUser();
        ProductSpu s1 = randomSpu();
        randomEn(u2, s1);
        boolean present = this.sellerFavoriteService.userHasFavorite(u2.getId(), s1.getId());
        Assertions.assertEquals(present, true);
    }
    private ProductFavorite randomEn(UserMember u, ProductSpu s) {
        ProductFavorite sellerFavorite = RandomUtils.randomPojo(ProductFavorite.class, sf -> {
            sf.setId(null);
            sf.setProductSpu(s);
            sf.setUserMember(u);
        });
        this.productFavoriteRepository.save(sellerFavorite);
        return sellerFavorite;
    }
    private ProductSpu randomSpu() {
        ProductSpu spu = RandomUtils.randomPojo(ProductSpu.class, s -> {
            s.setId(null); s.setSeller(null); s.setProductSkus(null); s.setProductSpuDetails(null);
            s.setProductBrand(null); s.setProductCategory(null);
        });
        this.productSpuRepository.save(spu);
        return spu;
    }
    private UserMember randomUser() {
        UserMember userMember = RandomUtils.randomPojo(UserMember.class, s -> {
            s.setId(null);
        });
        this.userMemberRepository.save(userMember);
        return userMember;
    }
}
