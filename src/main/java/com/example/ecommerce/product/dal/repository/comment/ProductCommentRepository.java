package com.example.ecommerce.product.dal.repository.comment;

import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    Page<ProductComment> findAllByProductSpuId(Long productSpuId, Pageable pageable);
    List<ProductComment> findAllByProductSpuId(Long productSpuId);
    Optional<ProductComment> findByUserMemberIdAndProductSpuId(Long userId, Long spuId);
}
