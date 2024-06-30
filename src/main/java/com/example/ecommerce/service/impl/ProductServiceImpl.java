package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.*;
import com.example.ecommerce.domain.dto.SortProductType;
import com.example.ecommerce.domain.dto.ProductDto;
import com.example.ecommerce.domain.dto.ProductRequest;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.*;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.utils.SortUtils;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> mapToDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("ProductId", id + "")
                );
        return mapToDto(product);
    }

    @Override
    @Transactional
    public void saveOrUpdate(ProductRequest request) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Category category = categoryRepository.findById(request.getCategoryId()).get();
        Product product = Product.builder()
                .vendor(vendor)
                .language(new Language(request.getLanguage().getNameVn(), request.getLanguage().getNameEn()))
                .category(category)
                .description(request.getDescription())
                .build();
        product.notification(
                notificationRepository.save(
                        new Notification(
                                String.format(
                                        "%s vừa có sản phẩm mới, có lẽ bạn sẽ thích: %s",
                                       vendor.getShopName(),
                                       product.getLanguage().getNameVn()
                                ),
                                product
                        )
                )
        );
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> findProductByCategoryId(Long categoryId, int page) {
        return productRepository.findAllByCategoryId(
                        categoryId,
                        PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM))
                .stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByVendor() {
        String username = SecurityUtils.username();
        return productRepository.findAllByVendorUserUsername(username)
                .stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAll(int page, int numberOfItem) {
        Page<Product> productPages = productRepository
                .findAll(PageRequest.of(page, numberOfItem));
        SystemUtils.totalPage = productPages.getTotalPages();
        return productPages.getContent()
                .stream()
                .map(e -> mapToDto(e))
                .collect(Collectors.toList());
    }


    @Override
    public boolean productWasBoughtByUser(Long productId, String username) {
        return false;
//        return billRepository.existsByOrderBillStatusAndOrderProductIdAndOrderUserUsername(Status.SUCCESS,productId, username);
    }


    @Override
    public List<ProductDto> searchProduct(Long categoryId,
                                          Long vendorId,
                                          String query,
                                          Integer startPrice,
                                          Integer endPrice,
                                          SortProductType sortProductType,
                                          int page) {

        List<Product> listProducts = productRepository.findAll();
        List<ProductDto> products = listProducts.stream()
                .filter(product -> {
                    if (categoryId <= 0) return true;
                    return product.getCategory().getId() == categoryId;
                })
                .filter(product -> {
                    if (vendorId <= 0) return true;
                    return product.getVendor().getId() == vendorId;
                })
                .filter(product -> {
                    String regex = ".*" + query + ".*";
                    if (query.isBlank() || query.isEmpty()) return true;
                    return (product.getLanguage().getNameEn().matches(regex) ||
                            product.getLanguage().getNameVn().matches(regex));
                })
                .filter(product -> {
                    if (startPrice == 0 && endPrice == 0) return true;
                    return product.getStocks()
                            .stream()
                            .anyMatch(stock -> stock.getPrice() >= startPrice
                                    && stock.getPrice() <= endPrice);
                })
                .map(product -> mapToDto(product))
                .collect(Collectors.toList());
        SystemUtils.totalPage = (int) Math.ceil((double) products.size() / (double) SystemUtils.NUMBER_OF_ITEM);
        products = products.stream()
                .skip(page * SystemUtils.NUMBER_OF_ITEM)
                .limit(Math.min(page * SystemUtils.NUMBER_OF_ITEM + SystemUtils.NUMBER_OF_ITEM, products.size()))
                .collect(Collectors.toList());
        SortUtils.sortProduct(sortProductType, products);
        return products;
    }

    public ProductDto mapToDto(Product product) {
        if (product.getStocks() != null) {
            product.getStocks().stream().forEach(e -> {
                e.setProduct(null);
            });
        }
        if (product.getEvaluations() != null) {
            product.getEvaluations().stream().forEach(e -> {
                e.setProduct(null);
                e.setUser(User.builder()
                        .username(e.getUser().getUsername())
                        .avatar(e.getUser().getAvatar())
                        .build());
            });
            Collections.sort(product.getEvaluations(),
                    (v1, v2) -> v2.getModifiedDate()
                            .compareTo(v1.getModifiedDate()));
        }
        ProductDto productDto = mapper.map(product, ProductDto.class)
                .toBuilder()
                .build();
        return productDto.toBuilder()
                .vendor(VendorServiceImpl.mapToDto(product.getVendor()))
                .numberOfProductSold(ProductSortServiceImpl
                        .getTotalSeller(productDto))
                .build();
    }


}
