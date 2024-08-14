package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.auth.User;
import com.example.ecommerce.domain.entities.auth.Vendor;
import com.example.ecommerce.domain.entities.product.Category;
import com.example.ecommerce.domain.entities.product.Product;
import com.example.ecommerce.domain.entities.product.ProductBrand;
import com.example.ecommerce.domain.entities.product.recommendation.ProductActionCache;
import com.example.ecommerce.domain.entities.product.recommendation.ProductSimilarity;
import com.example.ecommerce.domain.model.binding.FilterProductRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.algorithm.Similarity;
import com.example.ecommerce.service.algorithm.search.FilterData;
import com.example.ecommerce.service.algorithm.search.ProductFilterFactory;
import com.example.ecommerce.service.algorithm.search.ProductFilterStrategy;
import com.example.ecommerce.service.algorithm.sort.ProductSortFactory;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.ecommerce.domain.entities.product.recommendation.TypeAction.CLICK_PRODUCT;
import static com.example.ecommerce.service.event.Event.EventType.PRODUCT_CREATE;
import static com.example.ecommerce.service.event.Event.getInstance;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductInventoryRepository productInventoryRepository;
    private final ProductCacheRepository productCacheRepository;
    private final UserRepository userRepository;
    private final ProductSimilarityRepository productSimilarityRepository;

    @Override
    public APIResponse<?> findById(Long id) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("You aren't login"));
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException(String.format("Product with id %s not found", id)));
        saveProductSimilarity(product, user);
        return apiResponse("get product by id",  new ProductDetailsViewModel(product));
    }

    @Override
    public   APIResponse<?> save(ProductRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("You aren't login"));
        Product product = Product.builder()
                .price(request.getPrice())
                .description(request.getDescription())
                .combination(request.isCombination())
                .language(request.getLanguage())
                .productBrand(ProductBrand.builder().id(request.getBrandId()).build())
                .category(Category.builder().id(request.getCategoryId()).build())
                .vendor(Vendor.builder().id(user.getUserTypeId()).build())
                .build();
        Product saved = productRepository.save(product);
        new Thread(() -> getInstance().postEvent(PRODUCT_CREATE, saved)).start();
        final ProductDetailsViewModel response = new ProductDetailsViewModel(saved);
        return apiResponse("created product", response);
    }

    @Override
    public APIListResponse<ProductGalleryModelView> productRecommendation(Long productId) {
        ProductActionCache productActionCache = productCacheRepository
                .findByProductIdAndUserUsername(productId, SecurityUtils.getUsername())
                .orElseThrow(() -> new GeneralException("Not found cache product id: " + productId));
        APIListResponse<ProductGalleryModelView> response = responseAPI(
                null,
                productActionCache.getProductSimilarities()
                        .stream()
                        .sorted((s1, s2) -> s2.getSimilarity().compareTo(s1.getSimilarity()))
                        .map(pa -> pa.getProduct())
                        .toList()
        );
        response.setMessage(response.getData().get(0).getName());
        return response;
    }

    @Override
    public APIListResponse<ProductGalleryModelView> filterProduct(FilterProductRequest filterProductRequest) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            filterProductRequest.getData().entrySet().stream().forEach(entry -> {
                ProductFilterStrategy filterStrategy = ProductFilterFactory.getInstance(entry.getKey());
                filterStrategy.setFilterData(new FilterData(criteriaBuilder, root, entry.getValue()));
                Predicate condition = filterStrategy.filter();
                predicates.add(condition);
            });
            Predicate predicate = null;
            for (var pre : predicates) predicate = predicate != null ? criteriaBuilder.and(pre, predicate) : pre;
            return predicate;
        };
        PageRequest pageRequest = PageRequest.of(filterProductRequest.getPage() - 1, filterProductRequest.getLimit());
        Page<Product> pageProducts = productRepository.findAll(specification, pageRequest);
        List<Product> products = ProductSortFactory.getInstance(filterProductRequest.getSortType()).sort(pageProducts.getContent());
        return responseAPI(pageProducts, products);
    }

    //{end}////////////////////////////////////////////////////////////////////////////////
    private APIListResponse<ProductGalleryModelView> responseAPI(
            Page<Product> page,
            List<Product> products
    ) {
        APIListResponse<ProductGalleryModelView> response = new APIListResponse<>(
                "ok",
                0,
                1,
                HttpStatus.OK.value(),
                page != null ? page.getNumber() : -1,
                page != null ? page.getSize() : -1,
                page != null ? page.getTotalPages() : -1,
                products.stream().map(ProductGalleryModelView::new).toList());
        return response;
    }
    private APIListResponse<ProductGalleryModelView> responseAPI(
            Page<Product> page
    ) {
        APIListResponse<ProductGalleryModelView> response = new APIListResponse<>(
                "ok",
                0,
                1,
                HttpStatus.OK.value(),
                page != null ? page.getNumber() : -1,
                page != null ? page.getSize() : -1,
                page != null ? page.getTotalPages() : -1,
                page.getContent().stream().map(ProductGalleryModelView::new).toList());
        return response;
    }


    /**
     * thread for saveProductActionCache
     */
    private void saveProductSimilarity(final Product product, final User user) {
//        new Thread(() -> {
        ProductActionCache productActionCache = productCacheRepository.save(
                ProductActionCache.builder()
                        .product(product)
                        .typeAction(CLICK_PRODUCT)
                        .user(user)
                        .build()
        );
        List<Product> products = productRepository.findAllDifferentId(productActionCache.getProduct().getId());

        products.stream().forEach((p) -> {
            double similarity1 = Similarity.similarity(
                    productActionCache.getProduct().getLanguage().getNameEn(),
                    p.getLanguage().getNameEn()
            );
            double similarity2 = Similarity.similarity(
                    productActionCache.getProduct().getLanguage().getNameVn(),
                    p.getLanguage().getNameVn()
            );
            ProductSimilarity productSimilarity = ProductSimilarity.builder()
                    .similarity((similarity2 + similarity1) / 2)
                    .productActionCache(productActionCache)
                    .product(p)
                    .build();
            productSimilarityRepository.save(productSimilarity);
        });
//        }).start();
    }
}



