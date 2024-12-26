package com.example.ecommerce.finance.dal.repo.transaction;

import com.example.ecommerce.finance.dal.dataobject.transaction.Transaction;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.fromUser.id = :userId or " +
            "t.toUser.id = :userId order by t.id desc")
    List<Transaction> findAllByUserId(Long userId);

    @Query("select t from Transaction t where (t.fromUser.id = :userId or t.toUser.id = :userId) " +
            "\n and (t.createdDate between :startDate and :endDate) " +
            "\n order by t.id desc")
    Page<Transaction> findAllByUserId(@Param("userId") Long userId,
                                      @Param("startDate") LocalDateTime startDate,
                                      @Param("endDate")LocalDateTime endDate,
                                      Pageable pageable);

    @Query("select t from Transaction t where t.createdDate between :startDate and :endDate\n" +
            " order by t.id desc")
    Page<Transaction> findAll(@Param("startDate") LocalDateTime startDate,
                              @Param("endDate")LocalDateTime endDate,
                              Pageable pageable);
}
