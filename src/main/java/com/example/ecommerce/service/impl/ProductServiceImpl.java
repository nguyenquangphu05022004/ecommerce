package com.example.ecommerce.service.impl;

import com.example.ecommerce.config.SecurityUtils;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Status;
import com.example.ecommerce.entity.Vendor;
import com.example.ecommerce.repository.BillRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.VendorRepository;
import com.example.ecommerce.service.IProductService;
import com.example.ecommerce.utils.Convert;
import com.example.ecommerce.utils.SystemUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("productService")
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final VendorRepository vendorRepository;
    private final BillRepository billRepository;


    @Override
    public List<ProductDto> records() {
        return GenericService.records(productRepository, Convert.PRO);
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
        return (ProductDto) Convert.PRO.toDto(
                GenericService.findById(productRepository, id)
        );
    }

    @Override
    @Transactional
    public ProductDto saveOrUpdate(ProductDto productDto) {
        Vendor vendor = vendorRepository.findByUserUsername(SecurityUtils.username());
        Product product = new Product();
        if (productDto.getId() != null) {
            return null;
        } else {
            product = (Product) Convert.PRO.toEntity(productDto);
        }
        product.setVendor(vendor);
        return (ProductDto) Convert.PRO.toDto(productRepository.save(product));

    }

    @Override
    public List<ProductDto> findProductByCategoryId(Long categoryId, int page) {
//        return productRepository.findAllByCategoryId(categoryId, PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM))
//                .stream()
//                .map(e -> (ProductDto) Convert.PRO.toDto(e))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<ProductDto> search(String query, Long categoryId,
                                   Long vendorId, Integer startPrice,
                                   Integer endPrice, int page) {
//        Pageable paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM);
//        Page<Product> pages = null;
//        if(vendorId != null) {
//            if(query == null && categoryId == 0 && startPrice == 0 && endPrice == 0) {
//                pages = productRepository.findAllByVendorId(vendorId, paging);
//            } else if(query == null && categoryId == 0 && startPrice != 0 && endPrice != 0) {
//                pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndVendorId(vendorId, startPrice, endPrice, paging);
//            } else if(query == null && categoryId != null) {
//                if(startPrice != 0 && endPrice != 0) {
//                    pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndCategoryIdAndVendorId(vendorId, categoryId, startPrice, endPrice, paging);
//                } else {
//                    pages = productRepository.findAllByCategoryIdAndVendorId(vendorId, categoryId, paging);
//                }
//            } else if(query != null && categoryId == 0) {
//                if(startPrice != 0 && endPrice != 0) {
//                    pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndProductNameAndVendorId(vendorId,query, startPrice, endPrice, paging);
//                } else {
//                    pages = productRepository.findAllByProductNameAndVendorId(vendorId, query, paging);
//                }
//            } else if(query != null && categoryId != 0) {
//                if(startPrice != 0 && endPrice != 0) {
//                    pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndProductNameAndCategoryIdAndVendorId(vendorId, query, categoryId, startPrice, endPrice, paging);
//                } else {
//                    pages = productRepository.findAllByProductNameAndCategoryIdAndVendorId(vendorId, query, categoryId, paging);
//                }
//            }
//        } else {
//            if(query == null && categoryId == 0 && startPrice != 0 && endPrice != 0) {
//                pages = productRepository.findAllByPriceBetweenStartPriceAndEndPrice(startPrice, endPrice, paging);
//            } else if(query == null && categoryId != null) {
//                if(startPrice != 0 && endPrice != 0) {
//                    pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndCategoryId(categoryId, startPrice, endPrice, paging);
//                } else {
//                    pages = productRepository.findAllByCategoryId(categoryId, paging);
//                }
//            } else if(query != null && categoryId == 0) {
//                if(startPrice != 0 && endPrice != 0) {
//                    pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndProductName(query, startPrice, endPrice, paging);
//                } else {
//                    pages = productRepository.findAllByProductName(query, paging);
//                }
//            } else if(query != null && categoryId != null) {
//                if(startPrice != 0 && endPrice != 0) {
//                    pages = productRepository.findAllByPriceBetweenStartPriceAndEndPriceAndProductNameAndCategoryId(query, categoryId, startPrice, endPrice, paging);
//                } else {
//                    pages = productRepository.findAllByProductNameAndCategoryId(query, categoryId, paging);
//                }
//            }
//        }
//        SystemUtils.totalPage = pages.getTotalPages();
//        List<Product> products = pages.getContent();
//        return products.stream()
//                .map((entity) -> (ProductDto) Convert.PRO.toDto(entity))
//                .collect(Collectors.toList());
        return null;
    }

    @Override
    public List<ProductDto> findAllByVendor() {
        String username = SecurityUtils.username();
        return productRepository.findAllByVendorUserUsername(username)
                .stream()
                .map(e -> (ProductDto) Convert.PRO.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllByVendor(Long vendorId, Integer page) {
        Pageable paging = PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM);
        Page<Product> productPages = productRepository.findAllByVendorId(vendorId, paging);
        SystemUtils.totalPage = productPages.getTotalPages();
        return productPages.getContent()
                .stream()
                .map(e -> (ProductDto) Convert.PRO.toDto(e))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAll(int page) {
        Page<Product> productPages = productRepository.findAll(PageRequest.of(page, SystemUtils.NUMBER_OF_ITEM));
        SystemUtils.totalPage = productPages.getTotalPages();
        return productPages.getContent()
                .stream()
                .map(e -> (ProductDto) Convert.PRO.toDto(e))
                .collect(Collectors.toList());
    }



    @Override
    public boolean productWasBoughtByUser(Long productId, String username) {
        return false;
//        return billRepository.existsByOrderBillStatusAndOrderProductIdAndOrderUserUsername(Status.SUCCESS,productId, username);
    }

}
