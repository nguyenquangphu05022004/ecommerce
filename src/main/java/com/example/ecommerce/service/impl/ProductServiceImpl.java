package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SortUtils;
import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Language_;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Product_;
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
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

        if (request.getBrandId() == null && (request.getName().isEmpty() || request.getName().isBlank()))
            throw new GeneralException(String.format("CAN_NOT_BE_EMPTY", "Brand"));

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
    public List<ProductDto> findProductByCategoryId(Long categoryId, int page) {
        Page<Product> result = productRepository.findAllByCategoryId(
                categoryId,
                PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM));
        return mapper.toDtoList(result.getContent());
    }

    @Override
    public List<ProductDto> findAllByVendor() {
        String username = SecurityUtils.username();
        List<Product> products = productRepository.findAllByVendorUserUsername(username);
        return mapper.toDtoList(products);
    }

    @Override
    public List<ProductDto> findAllByVendorId(Long id) {
        List<Product> products = productRepository.findAllByVendorId(id);
        return mapper.toDtoList(products);
    }

    @Override
    public List<ProductDto> findAll(int page, int numberOfItem) {
        Page<Product> result = productRepository
                .findAll(PageRequest.of(page, numberOfItem));
        return mapper.toDtoList(result.getContent());
    }

    @Override
    public boolean productWasBoughtByUser(Long productId, String username) {
        return false;
    }

    @Override
    public List<ProductDto> searchProduct(FilterInputRequestProduct filterInputProduct) {
        Specification<Product> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filterInputProduct.getPairs().forEach(pair -> {
                switch (pair.getKey()) {
                    case PRODUCT_NAME:
                        predicates.add(criteriaBuilder.like(root.join(Product_.language).get(Language_.nameEn), "%" + pair.getValue().toLowerCase() + "%"));
                        predicates.add(criteriaBuilder.like(root.join(Product_.language).get(Language_.nameVn), "%" + pair.getValue().toLowerCase() + "%"));
                        break;
                    case CATEGORY_ID:
                        predicates.add(criteriaBuilder.equal(root.join("category").get("id"),pair.getValue()));
                        break;
                    case BRAND_ID:
                        predicates.add(criteriaBuilder.equal(root.join("brand").get("id"),pair.getValue()));
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
            for(Predicate element : predicates) {
                Predicate tmp = criteriaBuilder.and(element);
                if(predicate == null) predicate = tmp;
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
        List<ProductDto> products = mapper.toDtoList(pageProducts.getContent());
        SortUtils.sortProduct(filterInputProduct.getSortProductType(), products);
        return products;
    }


}
