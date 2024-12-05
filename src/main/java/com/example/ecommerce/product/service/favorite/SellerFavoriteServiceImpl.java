package com.example.ecommerce.product.service.favorite;

import com.example.ecommerce.product.dal.repository.favorite.SellerFavoriteRepository;
import com.example.ecommerce.product.dal.dataobject.favorite.SellerFavorite;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("seller")
public class SellerFavoriteServiceImpl implements FavoriteService{
    private final SellerFavoriteRepository sellerFavoriteRepository;
    @Override
    public Boolean createFavorite(Long userMemberId, Long targetId) {
        SellerFavorite sellerFavorite = SellerFavorite.builder()
                .seller(Seller.builder().id(targetId).build())
                .userMember(UserMember.builder().id(userMemberId).build())
                .build();
        this.sellerFavoriteRepository.save(sellerFavorite);
        return true;
    }

    @Override
    public Boolean deleteFavorite(Long userMemberId, Long targetId) {
        Optional<SellerFavorite> op = this.sellerFavoriteRepository
                .findByUserMemberIdAndSellerId(userMemberId, targetId);
        if(op.isPresent()) {
            this.sellerFavoriteRepository.delete(op.get());
        }
        return false;
    }

    @Override
    public Boolean userHasFavorite(Long userId, Long targetId) {
        return this.sellerFavoriteRepository
                .findByUserMemberIdAndSellerId(userId, targetId)
                .isPresent();
    }
}
