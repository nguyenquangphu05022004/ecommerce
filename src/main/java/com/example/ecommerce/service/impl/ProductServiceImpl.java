package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.entities.Role;
import com.example.ecommerce.domain.entities.User;
import com.example.ecommerce.domain.entities.Vendor;
import com.example.ecommerce.domain.entities.Category;
import com.example.ecommerce.domain.entities.Product;
import com.example.ecommerce.domain.entities.ProductBrand;
import com.example.ecommerce.domain.entities.product.recommendation.ProductActionCache;
import com.example.ecommerce.domain.entities.product.recommendation.ProductSimilarity;
import com.example.ecommerce.domain.model.binding.ProductFilterRequest;
import com.example.ecommerce.domain.model.binding.ProductRequest;
import com.example.ecommerce.domain.model.modelviews.product.ProductDetailsViewModel;
import com.example.ecommerce.domain.model.modelviews.product.ProductGalleryModelView;
import com.example.ecommerce.domain.model.modelviews.product.ProductModelView;
import com.example.ecommerce.domain.response.APIListResponse;
import com.example.ecommerce.domain.response.APIResponse;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.algorithm.search.product.DataFilter;
import com.example.ecommerce.service.algorithm.search.product.StrategyFilter;
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
import static com.example.ecommerce.service.algorithm.search.product.FactoryFilter.*;
import static com.example.ecommerce.service.algorithm.sort.SortFactory.*;
import static com.example.ecommerce.service.event.Event.EventType.PRODUCT_CREATE;
import static com.example.ecommerce.service.event.Event.getInstance;
import static com.example.ecommerce.service.impl.VendorServiceImpl.apiResponse;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public APIResponse<?> findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new GeneralException(
                        String.format("Product with id %s not found", id))
                );
        return apiResponse(
                "get product by id",
                new ProductDetailsViewModel(product));
    }

    @Override
    public APIResponse<?> save(ProductRequest request) {
        User user = userRepository.findByUsernameIgnoreCase(SecurityUtils.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("You aren't login"));
        Product product = Product.builder()
                .description(request.getDescription())
                .nameEn(request.getNameEn())
                .nameVn(request.getNameVn())
                .productBrand(ProductBrand.builder().id(request.getBrandId()).build())
                .category(Category.builder().id(request.getCategoryId()).build())
                .vendor((Vendor) user)
                .slug(request.getSlug())
                .build();
        Product saved = productRepository.save(product);
        new Thread(() -> getInstance().postEvent(PRODUCT_CREATE, saved)).start();
        final ProductModelView response = new ProductModelView(saved);
        return apiResponse("created product", response);
    }


    @Override
    public APIListResponse<ProductGalleryModelView> productFilter(
            ProductFilterRequest productFilterRequest
    ) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            productFilterRequest.getData().entrySet().stream().forEach(entry -> {

                StrategyFilter strategyFilter = getStrategyFilter(entry.getKey());
                strategyFilter.setDataFilter(new DataFilter(
                        criteriaBuilder, root, entry.getValue()
                ));
                Predicate condition = strategyFilter.filter();
                predicates.add(condition);
            });
            Predicate predicate = null;
            for (var pre : predicates)
                predicate = predicate != null
                        ? criteriaBuilder.and(pre, predicate) : pre;
            return predicate;
        };
        PageRequest pageRequest = PageRequest.of(
                productFilterRequest.getPage() - 1,
                productFilterRequest.getLimit()
        );

        Page<Product> pageProducts = productRepository.findAll(
                specification,
                pageRequest
        );

        List<Product> products = getSortStrategy(
                productFilterRequest.getSortType()
        ).sort(pageProducts.getContent());
        return responseAPI(pageProducts, products);
    }

    private APIListResponse<ProductGalleryModelView> responseAPI(
            Page<Product> page,
            List<Product> products
    ) {
        APIListResponse<ProductGalleryModelView> response = new APIListResponse<>(
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
                HttpStatus.OK.value(),
                page != null ? page.getNumber() : -1,
                page != null ? page.getSize() : -1,
                page != null ? page.getTotalPages() : -1,
                page.getContent().stream().map(ProductGalleryModelView::new).toList());
        return response;
    }
}



