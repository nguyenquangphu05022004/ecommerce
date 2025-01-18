package com.example.ecommerce.product.init;

import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.product.dal.dataobject.brand.ProductBrand;
import com.example.ecommerce.product.dal.dataobject.category.ProductCategory;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.properties.ProductPropertyValue;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSkuProperty;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.brand.ProductBrandRepository;
import com.example.ecommerce.product.dal.repository.category.ProductCategoryRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyValueRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuPropertyRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.Seller;
import com.example.ecommerce.system.dal.repository.user.SellerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.ecommerce.product.dal.dataobject.properties.ProductProperty.ProductPropertyType.SKU;

@RequiredArgsConstructor
@Component
public class ProductInit {

    private final ProductCategoryRepository categoryRepository;
    private final ProductBrandRepository productBrandRepository;
    private final ProductSpuRepository spuRepository;
    private final ProductSkuRepository skuRepository;
    private final ProductPropertyRepository productPropertyRepository;
    private final ProductPropertyValueRepository productPropertyValueRepository;
    private final ProductSkuPropertyRepository skuPropertyRepository;
    private final SellerRepository sellerRepository;
    @PostConstruct
    public void initProduct() {
//        initSeller();
//        initCategory();
//        initBrand();
//        initProperty();
//        initProperty();
//        initPropertyValue();
//        initSpu();
//        initSku();
    }
    private void initBrand() {
        if(CollUtils.size(productBrandRepository.findAll()) == 0) {
            productBrandRepository.saveAll(List.of(
                    ProductBrand.builder().name("Adidas").slug("adidas").avatar("https://upload.wikimedia.org/wikipedia/commons/2/24/Adidas_logo.png").build(),
                    ProductBrand.builder().name("Apple").slug("apple").avatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqKy_sLv3jeyXshVqcHcFuLIljHqik9j43kg&s").build(),
                    ProductBrand.builder().name("Samsung").slug("samsung").avatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0JZbBxPreM-Y1rjCUTy3fkJKry--8PFGvQg&s").build(),
                    ProductBrand.builder().name("Louis Vuitton").slug("louis-vuitton").avatar("https://e7.pngegg.com/pngimages/175/15/png-clipart-louis-vuitton-logo-icons-logos-emojis-iconic-brands-thumbnail.png").build()
            ));
        }
    }
    private void initCategory() {
        if(CollUtils.size(categoryRepository.findAll()) == 0) {
            categoryRepository.saveAll(List.of(
                    ProductCategory.builder().name("Fashions")
                            .thumbnail("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZcQH4BwSzPZ0NVC20Eem3QKleNO8SZgl6bw&s")
                            .build(),
                    ProductCategory.builder().name("Electrical Products")
                            .thumbnail("https://img.freepik.com/premium-photo/electrical-equipment-various-electric-products-store-shelve_106035-315.jpg?semt=ais_hybrid")
                            .build(),
                    ProductCategory.builder().name("Sports")
                            .thumbnail("https://img.freepik.com/free-photo/sports-tools_53876-138077.jpg")
                            .build()
            ));

            ProductCategory sports = categoryRepository.findByName("Sports").get();
            ProductCategory fashions = categoryRepository.findByName("Fashions").get();
            categoryRepository.saveAll(List.of(
                    ProductCategory.builder().name("Balls").categoryParent(sports).build()
            ));
            categoryRepository.saveAll(List.of(
                    ProductCategory.builder().name("Clothes").categoryParent(fashions).build(),
                    ProductCategory.builder().name("Shoes").categoryParent(fashions).build(),
                    ProductCategory.builder().name("Hats").categoryParent(fashions).build(),
                    ProductCategory.builder().name("Gloves").categoryParent(fashions).build()
            ));

            ProductCategory electricalProducts = categoryRepository.findByName("Electrical Products").get();
            categoryRepository.saveAll(List.of(
                    ProductCategory.builder().name("Laptops").categoryParent(electricalProducts).build(),
                    ProductCategory.builder().name("Headphones").categoryParent(electricalProducts).build()
            ));

        }
    }
    private void initSpu() {
        if(CollUtils.size(spuRepository.findAll()) == 0) {
            List<ProductSpu> spu = List.of(
                    ProductSpu.builder()
                            .name("Quan jean")
                            .productBrand(ProductBrand.builder().id(1L).build())
                            .enable(true).maxPrice(250_000).minPrice(150_000)
                            .description("Quan jean")
                            .productCategory(categoryRepository.findByName("Clothes").get())
                            .sendFrom("TP.HCM")
                            .seller(Seller.builder().id(2l).build())
                            .build()
            );
            spuRepository.saveAll(spu);
        }
    }
    private void initSku() {
        if(CollUtils.size(skuRepository.findAll()) == 0) {
            ProductSku sku1 = ProductSku.builder().productSpu(ProductSpu.builder().id(3l).build())
                    .price(620_000).quantity(100)
                    .build();
            ProductSku sku2 = ProductSku.builder().productSpu(ProductSpu.builder().id(3l).build())
                    .price(600_000).quantity(55)
                    .build();
            skuRepository.save(sku1);
            skuRepository.save(sku2);
            List<ProductSkuProperty> sku1Properties = List.of(ProductSkuProperty.builder().productSku(sku1)
                            .productProperty(ProductProperty.builder().id(1l).build())
                            .productPropertyValue(ProductPropertyValue.builder().id(5l).build())
                            .build(),
                    ProductSkuProperty.builder().productSku(sku1)
                            .productProperty(ProductProperty.builder().id(2l).build())
                            .productPropertyValue(ProductPropertyValue.builder().id(3l).build())
                            .build()
            );


            List<ProductSkuProperty> sku2Properties = List.of(ProductSkuProperty.builder().productSku(sku2)
                            .productProperty(ProductProperty.builder().id(1l).build())
                            .productPropertyValue(ProductPropertyValue.builder().id(6l).build())
                            .build(),
                    ProductSkuProperty.builder().productSku(sku2)
                            .productProperty(ProductProperty.builder().id(2l).build())
                            .productPropertyValue(ProductPropertyValue.builder().id(4l).build())
                            .build()
            );

            this.skuPropertyRepository.saveAll(sku1Properties);
            this.skuPropertyRepository.saveAll(sku2Properties);
        }
    }
    private void initProperty() {
        if(CollUtils.size(productPropertyRepository.findAll()) == 0) {
            List<ProductProperty> properties = List.of(
                    ProductProperty.builder().name("Size").productPropertyType(SKU).build(),
                    ProductProperty.builder().name("Color").productPropertyType(SKU).build()
            );
            this.productPropertyRepository.saveAll(properties);
        }
    }
    private void initPropertyValue() {
        if(CollUtils.size(productPropertyValueRepository.findAll()) == 0) {
            productPropertyValueRepository.saveAll(List.of(
                    ProductPropertyValue.builder().propertyValue("42")
                            .productProperty(ProductProperty.builder().id(1L).build()).build(),
                    ProductPropertyValue.builder().propertyValue("43")
                            .productProperty(ProductProperty.builder().id(1L).build()).build(),
                    ProductPropertyValue.builder().propertyValue("White")
                            .productProperty(ProductProperty.builder().id(2L).build()).build(),
                    ProductPropertyValue.builder().propertyValue("Blue")
                            .productProperty(ProductProperty.builder().id(2L).build()).build(),
                    ProductPropertyValue.builder().propertyValue("M")
                            .productProperty(ProductProperty.builder().id(1L).build()).build(),
                    ProductPropertyValue.builder().propertyValue("XL")
                            .productProperty(ProductProperty.builder().id(1L).build()).build()
            ));
        }
    }
    private void initSeller() {
        if(CollUtils.size(sellerRepository.findAll()) == 0) {
            Seller seller = Seller.builder()
                    .shopName("Shop Test1")
                    .avatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcb8I77Ue_XQcR3jbnDvni5lReEJ6njFPaWw&s")
                    .shopImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcb8I77Ue_XQcR3jbnDvni5lReEJ6njFPaWw&s")
                    .build();
            Seller seller1 = Seller.builder()
                    .shopName("Test Shop2")
                    .avatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcb8I77Ue_XQcR3jbnDvni5lReEJ6njFPaWw&s")
                    .shopImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTcb8I77Ue_XQcR3jbnDvni5lReEJ6njFPaWw&s")
                    .build();
            sellerRepository.save(seller);
            sellerRepository.save(seller1);
        }
    }
}
