package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.enums.CustomStatusCode;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
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
@Transactional
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDetailsViewModel findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException(String.format("Product with id %s not found", id)));
        return new ProductDetailsViewModel(product);
    }

    @Override
    public void save(ProductRequest request) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username())
                .orElseThrow(() -> new UsernameNotFoundException("You are not role VENDOR, so you can't create product"));
        Product product = Product.builder()
                .price(request.getPrice())
                .description(request.getDescription())
                .combination(request.isCombination())
                .language(request.getLanguage())
                .productBrand(ProductBrand.builder().id(request.getBrandId()).build())
                .category(Category.builder().id(request.getCategoryId()).build())
                .vendor(vendor)
                .build();
        productRepository.save(product);
    }

    @Override
    public APIListResponse<ProductGalleryModelView> searchProduct(FilterInputRequestProduct filterInputProduct) {
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
                        predicates.add(criteriaBuilder.equal(root.join("productBrand").get("id"), pair.getValue()));
                    } else if (pair.getKey().startsWith(KeySearchRequest.PRICE.name())) {
                        String words[] = pair.getValue().split(";");
                        int x1 = Integer.parseInt(words[0]);
                        int x2 = Integer.parseInt(words[1]);
                        predicates.add(criteriaBuilder.between(root.joinList("stocks").get("price"), x1, x2));
                    } else if(pair.getKey().startsWith(KeySearchRequest.VENDOR_ID.name())) {
                        predicates.add(criteriaBuilder.equal(root.join("vendor").get("id"), pair.getValue()));
                    }
                });
                Predicate p1 = null;
                for (Predicate element : predicates) {
                    Predicate tmp = criteriaBuilder.and(element);
                    if (p1 == null) p1 = tmp;
                    p1 = criteriaBuilder.and(p1, tmp);
                }
                Predicate p2 = null;
                for (Predicate element : predicatesOfChildrenCategory) {
                    Predicate tmp = criteriaBuilder.or(element);
                    if (p2 == null) p2 = tmp;
                    p2 = criteriaBuilder.or(p2, tmp);
                }
                if (p2 == null) return p1;
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
        return responseAPI(pageProducts, products);
    }

    private APIListResponse<ProductGalleryModelView> responseAPI(
            Page<Product> page,
            List<Product> products
    ) {
        APIListResponse<ProductGalleryModelView> response = new APIListResponse<>(
                "ok",
                "",
                1,
                HttpStatus.OK.name(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                products.stream().map(ProductGalleryModelView::new).toList());
        return response;
    }
}
