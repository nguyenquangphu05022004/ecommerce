package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.test.AssertUtils;
import com.example.ecommerce.product.controller.comment.evaluation.vo.ProductEvaluationReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductEvaluation;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.comment.ProductEvaluationRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.ecommerce.frame.test.RandomUtils.randomList;
import static com.example.ecommerce.frame.test.RandomUtils.randomPojo;

@Import({ProductEvaluationServiceImpl.class})
class ProductEvaluationServiceImplTest  extends TestBase {

    @Autowired
    private ProductEvaluationService productEvaluationService;

    @Autowired
    private ProductPropertyRepository productPropertyRepository;
    @Autowired
    private ProductSpuRepository productSpuRepository;
    @Autowired
    private ProductEvaluationRepository productEvaluationRepository;
    @Test
    void testCreateProductEvaluation_success() {
        ProductProperty productProperty = randomListProperty().get(0);
        ProductSpu productSpu = randomListSpu().get(0);
        this.productPropertyRepository.save(productProperty);
        this.productSpuRepository.save(productSpu);

        ProductEvaluationReqVO reqVO = randomPojo(ProductEvaluationReqVO.class, s -> {
            s.setPropertyId(productProperty.getId());
            s.setProductSpuId(productSpu.getId());
        });

        ProductEvaluation res = this.productEvaluationService.createProductEvaluation(reqVO);

        AssertUtils.assertPojoEquals(res.getProductProperty(), productProperty);
        AssertUtils.assertPojoEquals(res.getProductSpu(), productSpu);
        ;
    }
    private List<ProductProperty> randomListProperty() {
        return randomList(ProductProperty.class, s -> s.setId(null));
    }
    private List<ProductSpu> randomListSpu() {
        return randomList(ProductSpu.class, s -> {
            s.setId(null);s.setProductBrand(null);s.setProductSkus(Collections.emptySet());s.setSeller(null);
            s.setProductCategory(null);
        });
    }
    @Test
    void testGetListEvaluationBySpuId_success() {
        ProductSpu productSpu = randomListSpu().get(0);
        List<ProductProperty> productProperties = randomListProperty();
        this.productSpuRepository.save(productSpu);
        this.productPropertyRepository.saveAll(productProperties);

        List<ProductEvaluation> collect = IntStream.range(0, productProperties.size()).mapToObj(index -> {
            ProductEvaluation res = randomPojo(ProductEvaluation.class, r -> {
                r.setProductSpu(productSpu);
                r.setProductProperty(productProperties.get(index));
            });
            return productEvaluationRepository.save(res);
        }).collect(Collectors.toList());
        List<ProductEvaluation> expected = this.productEvaluationService.getAllProductEvaluationByProductSpuId(productSpu.getId());
        Assertions.assertEquals(expected.size(), collect.size());

    }
}
