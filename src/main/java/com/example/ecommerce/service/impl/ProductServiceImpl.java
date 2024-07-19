package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SortUtils;
import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.NotificationRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.request.ProductRequest;
import com.example.ecommerce.service.response.APIListResponse;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final NotificationRepository notificationRepository;
    @Qualifier("productMapper")
    private final IMapper<Product, ProductRequest, ProductDto> mapper;

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public ProductDto findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return mapper.toDto(optionalProduct.get());
        }
        throw new GeneralException(String.format("Product id %s not found", id));
    }

    @Override
    @Transactional
    public void save(ProductRequest request) {
        ValidationUtils.fieldCheckNullOrEmpty(request.getCategoryId(), "ProductRequest CategoryId");
        ValidationUtils.fieldCheckNullOrEmpty(request.getDescription(), "ProductRequest Description");
        ValidationUtils.fieldCheckNullOrEmpty(request.getName(), "ProductRequest NameVn");
        ValidationUtils.fieldCheckNullOrEmpty(request.getNameEn(), "ProductRequest NameEn");

        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username())
                .orElseThrow(() -> new UsernameNotFoundException("You are not role VENDOR, so you can't create product"));

        Product product = mapper.toEntity(request)
                .toBuilder()
                .vendor(vendor)
                .build();

        Product saved = productRepository.save(product);
        saved.notify(notificationRepository);

    }

    @Override
    public APIListResponse<ProductDto> findProductByCategoryId(
            Long categoryId,
            int page,
            int limit
    ) {
        Page<Product> result = productRepository.findAllByCategoryId(
                categoryId,
                PageRequest.of(page, limit));
        return responseListProduct(page, limit, result.getTotalPages(), result.getContent());
    }

    @Override
    public APIListResponse<ProductDto> findAllByVendor(int page, int limit) {
        String username = SecurityUtils.username();
        Page<Product> productsPage = productRepository
                .findAllByVendorUserUsername(username, PageRequest.of(page, limit));
        return responseListProduct(page, limit, productsPage.getTotalPages(), productsPage.getContent());
    }

    @Override
    public APIListResponse<ProductDto> findAllByVendorId(Long id, int page, int limit) {
        Page<Product> result = productRepository
                .findAllByVendorId(id, PageRequest.of(page, limit));
        return responseListProduct(page, limit, result.getTotalPages(), result.getContent());
    }

    @Override
    public APIListResponse<ProductDto> findAll(int page, int numberOfItem) {
        Page<Product> result = productRepository
                .findAll(PageRequest.of(page, numberOfItem));
        return responseListProduct(page, numberOfItem, result.getTotalPages(), result.getContent());
    }

    @Override
    public boolean productWasBoughtByUser(Long productId, String username) {
        return false;
    }

    @Override
    public APIListResponse<ProductDto> searchProduct(FilterInputRequestProduct filterInputProduct) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filterInputProduct.getPairs().forEach(pair -> {
                switch (pair.getKey()) {
                    case PRODUCT_NAME:
                        predicates.add(criteriaBuilder.like(root.join("language").get("nameEn"), "%" + pair.getValue().toLowerCase() + "%"));
                        predicates.add(criteriaBuilder.like(root.join("language").get("nameVn"), "%" + pair.getValue().toLowerCase() + "%"));
                        break;
                    case CATEGORY_ID:
                        predicates.add(criteriaBuilder.equal(root.join("category").get("id"), pair.getValue()));
                        break;
                    case BRAND_ID:
                        predicates.add(criteriaBuilder.equal(root.join("brand").get("id"), pair.getValue()));
                        break;
                    case PRICE:
                        String words[] = pair.getValue().split(";");
                        int x1 = Integer.parseInt(words[0]);
                        int x2 = Integer.parseInt(words[1]);
                        predicates.add(criteriaBuilder.between(root.joinList("stocks").get("price"), x1, x2));
                        break;
                }
            });
            Predicate predicate = null;
            for (Predicate element : predicates) {
                Predicate tmp = criteriaBuilder.and(element);
                if (predicate == null) predicate = tmp;
                predicate = criteriaBuilder.and(predicate, tmp);
            }
            return predicate;
        };
        Page<Product> pageProducts = productRepository.findAll(
                specification,
                PageRequest.of(
                        filterInputProduct.getPage() - 1,
                        filterInputProduct.getLimit()
                ));
        List<Product> products = pageProducts.getContent();
        SortUtils.sortProduct(filterInputProduct.getSortProductType(), products);

        return responseListProduct(
                filterInputProduct.getPage(),
                filterInputProduct.getLimit(),
                pageProducts.getTotalPages(),
                products
        );
    }

    private APIListResponse<ProductDto> responseListProduct(int page, int limit, int totalPage, List<Product> products) {
        APIListResponse<ProductDto> response = new APIListResponse<>(
                "ok",
                "",
                1,
                HttpStatus.OK.name(),
                page,
                limit,
                totalPage,
                mapper.toDtoList(products));
        return response;
    }
}
