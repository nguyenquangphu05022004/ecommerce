package com.example.ecommerce.product.dal.repository.favorite;

import com.example.ecommerce.product.dal.dataobject.favorite.SellerFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerFavoriteRepository extends JpaRepository<SellerFavorite, Long> {
    List<SellerFavorite> findAllBySellerId(Long sellerId);
    Optional<SellerFavorite> findByUserMemberIdAndSellerId(Long userMemberId, Long sellerId);
}
