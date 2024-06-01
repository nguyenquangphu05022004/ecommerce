package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


//    @Query("select p from Product p where p.price >= :startPrice and p.price <= :endPrice")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPrice(@Param("startPrice") Integer start,
//                                                             @Param("endPrice") Integer end, Pageable pageable);
//
//    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
//            " where p.price >= :startPrice and p.price <= :endPrice")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndCategoryId(@Param("categoryId") Long categoryId,
//                                                                          @Param("startPrice") Integer start,
//                                                                          @Param("endPrice") Integer end,
//                                                                          Pageable pageable);
//
//    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId")
//    Page<Product> findAllByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);
//
//    @Query("select p from Product p " +
//            "where (lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%) " +
//            "and (p.price >= :startPrice and p.price <= :endPrice)")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndProductName(
//            @Param("query") String query,
//            @Param("startPrice") Integer start,
//            @Param("endPrice") Integer end,
//            Pageable pageable);
//
//    @Query("select p from Product p " +
//            "where (lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%)")
//    Page<Product> findAllByProductName(@Param("query") String query, Pageable pageable);
//
//    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
//            "where (lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%)" +
//            "and p.price >= :startPrice and p.price <= :endPrice")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndProductNameAndCategoryId(@Param("query") String query,
//                                                                                        @Param("categoryId") Long categoryId,
//                                                                                        @Param("startPrice") Integer start,
//                                                                                        @Param("endPrice") Integer end,
//                                                                                        Pageable pageable);
//
//    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
//            "where (lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%) " +
//            " and p.price >= :startPrice and p.price <= :endPrice")
//    Page<Product> findAllByProductNameAndCategoryId(@Param("query") String query,
//                                                    @Param("categoryId") Long categoryId,
//                                                    Pageable pageable);
//
//
//    @Query("select p from Product p where p.vendor.id = :vendorId and p.price >= :startPrice and p.price <= :endPrice")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndVendorId(@Param("vendorId") Long vendorId,
//                                                                        @Param("startPrice") Integer start,
//                                                                        @Param("endPrice") Integer end, Pageable pageable);
//
//    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
//            " where p.vendor.id = :vendorId and p.price >= :startPrice and p.price <= :endPrice")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndCategoryIdAndVendorId(
//            @Param("vendorId") Long vendorId,
//            @Param("categoryId") Long categoryId,
//            @Param("startPrice") Integer start,
//            @Param("endPrice") Integer end,
//            Pageable pageable);

    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
            " where  p.vendor.id = :vendorId")
    Page<Product> findAllByCategoryIdAndVendorId(
            @Param("vendorId") Long vendorId,
            @Param("categoryId") Long categoryId,
            Pageable pageable);

//    @Query("select p from Product p " +
//            "where p.vendor.id = :vendorId and " +
//            "(lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%) " +
//            " and (p.price >= :startPrice and p.price <= :endPrice)")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndProductNameAndVendorId(@Param("vendorId") Long vendorId,
//                                                                                      @Param("query") String query,
//                                                                                      @Param("startPrice") Integer start, @Param("endPrice") Integer end,
//                                                                                      Pageable pageable);
    @Query("select p from Product p " +
            "where p.vendor.id = :vendorId and " +
            "(lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%)")
    Page<Product> findAllByProductNameAndVendorId(@Param("vendorId") Long vendorId,
                                                  @Param("query") String query,
                                                  Pageable pageable);

//    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
//            "where p.vendor.id = :vendorId and (lower(p.language.nameEn) like %:query% or lower(p.language.nameVn) like %:query%) " +
//            " and (p.price >= :startPrice and p.price <= :endPrice)")
//    Page<Product> findAllByPriceBetweenStartPriceAndEndPriceAndProductNameAndCategoryIdAndVendorId(
//            @Param("vendorId") Long vendorId, @Param("query") String query,
//            @Param("categoryId") Long categoryId,
//            @Param("startPrice") Integer start,
//            @Param("endPrice") Integer end,
//            Pageable pageable);
    @Query("select p from Product p inner join Category c on p.category.id = c.id and c.id = :categoryId " +
            "where p.vendor.id = :vendorId and " +
            "(lower(p.language.nameEn) like  %:query% or lower(p.language.nameVn) like %:query%)")
    Page<Product> findAllByProductNameAndCategoryIdAndVendorId(
            @Param("vendorId") Long vendorId, @Param("query") String query,
            @Param("categoryId") Long categoryId,
            Pageable pageable);


    Page<Product> findAll(Pageable pageable);

    @Query("select pro from Product  pro order by (select avg(e.rating) from Evaluation e inner join Product p on e.product.id = p.id where p.id = pro.id)")
    Page<Product> findAllByAverageRatingWasSortDesc(Pageable pageable);

    Optional<Product> findById(Long id);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    List<Product> findAllByVendorUserUsername(String username);

    Page<Product> findAllByVendorId(Long vendorId, Pageable pageable);

}
