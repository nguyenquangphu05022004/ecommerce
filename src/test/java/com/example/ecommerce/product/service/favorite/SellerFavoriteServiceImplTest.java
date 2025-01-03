package com.example.ecommerce.product.service.favorite;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.dal.dataobject.favorite.SellerFavorite;
import com.example.ecommerce.product.dal.repository.favorite.SellerFavoriteRepository;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Import(SellerFavoriteServiceImpl.class)
class SellerFavoriteServiceImplTest extends TestBase {

    @Autowired
    private SellerFavoriteServiceImpl sellerFavoriteService;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserMemberRepository userMemberRepository;
    @Autowired
    private SellerFavoriteRepository sellerFavoriteRepository;
    @Test
    void test_createFavorite_success() {
        UserMember u1 = randomUser();
        UserMember u2 = randomUser();
        Seller s1 = randomSeller(u1);
        this.sellerFavoriteService.createFavorite(u2.getId(), s1.getId());
        boolean present = this.sellerFavoriteRepository.findByUserMemberIdAndSellerId(u2.getId(), s1.getId()).isPresent();

        Assertions.assertEquals(present, true);

    }

    @Test
    void test_deleteFavorite_success() {
        UserMember u1 = randomUser();
        UserMember u2 = randomUser();
        Seller s1 = randomSeller(u1);
        randomEn(u2, s1);
        this.sellerFavoriteService.deleteFavorite(u2.getId(), s1.getId());

        boolean present = this.sellerFavoriteRepository.findByUserMemberIdAndSellerId(u2.getId(), s1.getId()).isPresent();
        Assertions.assertEquals(present, false);


    }

    @Test
    void test_userHasFavorite_success() {
        UserMember u1 = randomUser();
        UserMember u2 = randomUser();
        Seller s1 = randomSeller(u1);
        randomEn(u2, s1);
        boolean present = this.sellerFavoriteService.userHasFavorite(u2.getId(), s1.getId());
        Assertions.assertEquals(present, true);
    }
    private SellerFavorite randomEn(UserMember u, Seller s) {
        SellerFavorite sellerFavorite = RandomUtils.randomPojo(SellerFavorite.class, sf -> {
            sf.setId(null);
            sf.setSeller(s);
            sf.setUserMember(u);
        });
        this.sellerFavoriteRepository.save(sellerFavorite);
        return sellerFavorite;
    }
    private Seller randomSeller(UserMember userMember) {
        Seller seller = RandomUtils.randomPojo(Seller.class, s -> {
            s.setId(null);
            s.setProductSpus(null);
        });
        this.sellerRepository.save(seller);
        return seller;
    }
    private UserMember randomUser() {
        UserMember userMember = RandomUtils.randomPojo(UserMember.class, s -> {
            s.setId(null);
        });
        this.userMemberRepository.save(userMember);
        return userMember;
    }
}
