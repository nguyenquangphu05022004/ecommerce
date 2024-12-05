package com.example.ecommerce.product.service.favorite;

import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.favorite.ProductFavoriteRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("product")
@RequiredArgsConstructor
public class ProductFavoriteServiceImpl implements FavoriteService{
    private final ProductFavoriteRepository productFavoriteRepository;
    @Override
    public Boolean createFavorite(Long userMemberId, Long targetId) {
        ProductFavorite productFavorite = ProductFavorite.builder()
                .productSpu(ProductSpu.builder().id(targetId).build())
                .userMember(UserMember.builder().id(userMemberId).build())
                .build();
        this.productFavoriteRepository.save(productFavorite);
       return true;
    }

    @Override
    public Boolean deleteFavorite(Long userMemberId, Long targetId) {
        Optional<ProductFavorite> op = this.productFavoriteRepository
                        .findByUserMemberIdAndProductSpuId(userMemberId, targetId);
        if(op.isPresent()) {
            this.productFavoriteRepository.delete(op.get());
        }
        return false;
    }

    @Override
    public Boolean userHasFavorite(Long userId, Long targetId) {
        return productFavoriteRepository
                .findByUserMemberIdAndProductSpuId(userId, targetId)
                .isPresent();
    }
}
