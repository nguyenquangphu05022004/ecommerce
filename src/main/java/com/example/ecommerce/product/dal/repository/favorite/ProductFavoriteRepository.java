package com.example.ecommerce.product.dal.repository.favorite;

import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface ProductFavoriteRepository extends JpaRepository<ProductFavorite, Long> {
    List<ProductFavorite> findAllByProductSpuId(Long productSpuId);
    Optional<ProductFavorite> findByUserMemberIdAndProductSpuId(Long userMemberId, Long productSpuId);

}
