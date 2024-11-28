package com.example.ecommerce.production.dal.repository.comment;

import com.example.ecommerce.production.dal.dataobject.comment.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
}
