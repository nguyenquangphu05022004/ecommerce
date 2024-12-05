package com.example.ecommerce.product.service.favorite;

import com.example.ecommerce.product.dal.dataobject.favorite.ProductFavorite;

public interface FavoriteService {
    Boolean createFavorite(Long userMemberId, Long targetId);
    Boolean deleteFavorite(Long userMemberId, Long targetId);
    Boolean userHasFavorite(Long userId, Long targetId);
}
