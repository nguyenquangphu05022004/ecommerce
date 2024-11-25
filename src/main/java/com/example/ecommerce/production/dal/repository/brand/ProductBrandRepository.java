package com.example.ecommerce.production.dal.repository.brand;

import com.example.ecommerce.production.dal.dataobject.brand.ProductBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBrandRepository extends JpaRepository<ProductBrand, Long> {
}
