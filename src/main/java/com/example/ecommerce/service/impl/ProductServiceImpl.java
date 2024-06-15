package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.domain.Vendor;
import com.example.ecommerce.domain.dto.ENUM.SortProductType;
import com.example.ecommerce.domain.dto.product.ProductDto;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.utils.SortUtils;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final ModelMapper mapper;

    @Override
    public List<ProductDto> records() {
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
    public Long count() {
        return productRepository.count();
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
    public ProductDto saveOrUpdate(ProductDto productDto) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Product product = null;
        if (productDto.getId() != null) {
            return null;
        } else {
            product = mapper.map(productDto, Product.class);
        }
        product.setVendor(vendor);
        return mapToDto(productRepository.save(product));

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
    public List<ProductDto> findAll(int page) {
        Page<Product> productPages = productRepository
                .findAll(PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM));
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

        Page<Product> productsPage = productRepository.findAll(PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM));
        SystemUtils.totalPage = productsPage.getTotalPages();
        List<ProductDto> products = productsPage.get()
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
        SortUtils.sortProduct(sortProductType, products);
        return products;
    }

    public ProductDto mapToDto(Product product) {
        product.getStocks().stream().forEach(e -> e.setProduct(null));
        product.getEvaluations().stream().forEach(e -> {
            e.setProduct(null);
            e.setUser(null);
        });
        return mapper.map(product, ProductDto.class)
                .toBuilder()
                .numberOfProductSold(
                        product
                        .getProductSeller()
                        .getNumberOfProductsSold()
                )
                .build();
    }

}
