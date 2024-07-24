package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.CustomStatusCode;
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
import com.example.ecommerce.service.dto.SortProductType;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.FilterInputRequestProduct;
import com.example.ecommerce.service.request.KeySearchRequest;
import com.example.ecommerce.service.request.ProductRequest;
import com.example.ecommerce.service.response.APIListResponse;
import com.example.ecommerce.service.response.APIResponse;
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
    public APIResponse<ProductDto> findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            return responseAPI(optionalProduct);
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
        return responseAPI(page + 1, limit, result.getTotalPages(), result.getContent());
    }

    @Override
    public APIListResponse<ProductDto> findAllByVendor(int page, int limit) {
        String username = SecurityUtils.username();
        Page<Product> productsPage = productRepository
                .findAllByVendorUserUsername(username, PageRequest.of(page, limit));
        return responseAPI(page + 1, limit, productsPage.getTotalPages(), productsPage.getContent());
    }

    @Override
    public APIListResponse<ProductDto> findAllByVendorId(Long id, int page, int limit) {
        Page<Product> result = productRepository
                .findAllByVendorId(id, PageRequest.of(page, limit));
        return responseAPI(page + 1, limit, result.getTotalPages(), result.getContent());
    }

    @Override
    public APIListResponse<ProductDto> findAll(int page, int numberOfItem) {
        Page<Product> result = productRepository
                .findAll(PageRequest.of(page, numberOfItem));
        return responseAPI(page + 1, numberOfItem, result.getTotalPages(), result.getContent());
    }

    @Override
    public boolean productWasBoughtByUser(Long productId, String username) {
        return false;
    }

    @Override
    public APIListResponse<ProductDto> searchProduct(FilterInputRequestProduct filterInputProduct) {
        Specification<Product> specification;

        if (filterInputProduct.getMapKey() == null || filterInputProduct.getMapKey().size() == 0) {
            specification = null;
        } else {
            specification = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                List<Predicate> predicatesOfChildrenCategory = new ArrayList<>();
                filterInputProduct.getMapKey().entrySet().forEach(pair -> {
                    if (pair.getKey().startsWith(KeySearchRequest.PRODUCT_NAME.name())) {
                        predicates.add(criteriaBuilder.like(root.join("language").get("nameEn"), "%" + pair.getValue().toLowerCase() + "%"));
                        predicates.add(criteriaBuilder.like(root.join("language").get("nameVn"), "%" + pair.getValue().toLowerCase() + "%"));
                    } else if (pair.getKey().startsWith(KeySearchRequest.CATEGORY_PARENT_ID.name())) {
                        predicates.add(criteriaBuilder.equal(root.join("category").get("parent").get("id"), pair.getValue()));
                    } else if (pair.getKey().startsWith(KeySearchRequest.CATEGORY_CHILDREN_ID.name())) {
                        predicatesOfChildrenCategory.add(criteriaBuilder.equal(root.join("category").get("id"), pair.getValue()));
                    } else if (pair.getKey().startsWith(KeySearchRequest.BRAND_ID.name())) {
                        predicates.add(criteriaBuilder.equal(root.join("brand").get("id"), pair.getValue()));
                    } else if (pair.getKey().startsWith(KeySearchRequest.PRICE.name())) {
                        String words[] = pair.getValue().split(";");
                        int x1 = Integer.parseInt(words[0]);
                        int x2 = Integer.parseInt(words[1]);
                        predicates.add(criteriaBuilder.between(root.joinList("stocks").get("price"), x1, x2));
                    }
                });
                Predicate p1 = null;
                for (Predicate element : predicates) {
                    Predicate tmp = criteriaBuilder.and(element);
                    if (p1 == null) p1 = tmp;
                    p1 = criteriaBuilder.and(p1, tmp);
                }
                Predicate p2 = null;
                for (Predicate element: predicatesOfChildrenCategory) {
                    Predicate tmp = criteriaBuilder.or(element);
                    if (p2 == null) p2 = tmp;
                    p2 = criteriaBuilder.or(p2, tmp);
                }
                if(p2 == null) return p1;
                return criteriaBuilder.and(p1, p2);
            };
        }
        Page<Product> pageProducts = specification != null ?
                productRepository.findAll(specification, PageRequest.of(
                        filterInputProduct.getPage() - 1,
                        filterInputProduct.getLimit()
                ))
                :
                productRepository.findAll(PageRequest.of(
                        filterInputProduct.getPage() - 1,
                        filterInputProduct.getLimit()
                ));
        List<Product> products = pageProducts.getContent();
        return responseAPI(
                filterInputProduct.getPage(),
                filterInputProduct.getLimit(),
                pageProducts.getTotalPages(),
                sort(filterInputProduct.getSortProductType(), products)
        );
    }

    @Override
    public APIListResponse<?> findAllByCategoryId(Long id, int page, int limit) {
        Page<Product> pageProducts = productRepository.findAllByCategoryId(id, PageRequest.of(page, limit));
        return responseAPI(page, limit, pageProducts.getTotalPages(), pageProducts.getContent());
    }

    private APIListResponse<ProductDto> responseAPI(int page, int limit, int totalPage, List<
            Product> products) {
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
    private APIResponse<ProductDto> responseAPI(Optional<Product> optionalProduct) {
        return new APIResponse<>(
                "OK",
                null,
                1,
                CustomStatusCode.SUCCESS.getNumber(),
                mapper.toDto(optionalProduct.get())
        );
    }

    private List<Product> sort(SortProductType type, List<Product> list) {
        var sortObj = new ProductSortServiceImpl();
        if(type == SortProductType.DEFAULT) {
            return sortObj.sortByDefault(list);
        } else if(type == SortProductType.PRICE) {
            return sortObj.sortByPrice(list);
        } else if(type == SortProductType.NUMBER_OF_SELLER) {
            return sortObj.sortByNumberOfSeller(list);
        } else if(type == SortProductType.RATE_AVERAGE) {
            return sortObj.sortByRateAverage(list);
        }
        return list;
    }
}
