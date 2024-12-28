package com.example.ecommerce.product.service.favorite;

public interface FavoriteService {
    Boolean createFavorite(Long userMemberId, Long targetId);
    Boolean deleteFavorite(Long userMemberId, Long targetId);
    Boolean userHasFavorite(Long userId, Long targetId);
}
