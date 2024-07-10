package com.example.ecommerce.service.impl;

import com.example.ecommerce.common.utils.SystemUtils;
import com.example.ecommerce.common.utils.ValidationUtils;
import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.service.dto.SortProductType;
import com.example.ecommerce.handler.exception.GeneralException;
import com.example.ecommerce.repository.NotificationRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.service.dto.ProductDto;
import com.example.ecommerce.service.mapper.IMapper;
import com.example.ecommerce.service.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new UsernameNotFoundException("You not login. Please login before create product"));
        Product product = mapper.toEntity(request)
                .toBuilder()
                .vendor(vendor)
                .build();

        productRepository.save(product);
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
    public List<ProductDto> searchProduct(Long categoryId,
                                          Long vendorId,
                                          String query,
                                          Integer startPrice,
                                          Integer endPrice,
                                          SortProductType sortProductType,
                                          int page) {

//        List<Product> listProducts = productRepository.findAll();
//        List<ProductDto> products = listProducts.stream()
//                .filter(product -> {
//                    if (categoryId <= 0) return true;
//                    return product.getCategory().getId() == categoryId;
//                })
//                .filter(product -> {
//                    if (vendorId <= 0) return true;
//                    return product.getVendor().getId() == vendorId;
//                })
//                .filter(product -> {
//                    String regex = ".*" + query + ".*";
//                    if (query.isBlank() || query.isEmpty()) return true;
//                    return (product.getLanguage().getNameEn().matches(regex) ||
//                            product.getLanguage().getNameVn().matches(regex));
//                })
//                .filter(product -> {
//                    if (startPrice == 0 && endPrice == 0) return true;
//                    return product.getStocks()
//                            .stream()
//                            .anyMatch(stock -> stock.getPrice() >= startPrice
//                                    && stock.getPrice() <= endPrice);
//                })
//                .map(product -> mapToDto(product))
//                .collect(Collectors.toList());
//        SystemUtils.totalPage = (int) Math.ceil((double) products.size() / (double) SystemUtils.NUMBER_OF_ITEM);
//        products = products.stream()
//                .skip(page * SystemUtils.NUMBER_OF_ITEM)
//                .limit(Math.min(page * SystemUtils.NUMBER_OF_ITEM + SystemUtils.NUMBER_OF_ITEM, products.size()))
//                .collect(Collectors.toList());
//        SortUtils.sortProduct(sortProductType, products);
        return null;
    }
}
