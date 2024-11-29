package com.example.ecommerce.production.dal.repository.comment;

import com.example.ecommerce.production.dal.dataobject.comment.ProductComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    Page<ProductComment> findAllByProductSpuId(Long productSpuId, Pageable pageable);
}
