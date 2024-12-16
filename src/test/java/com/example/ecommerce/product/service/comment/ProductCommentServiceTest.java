package com.example.ecommerce.product.service.comment;

import com.example.ecommerce.TestBase;
import com.example.ecommerce.frame.common.collection.CollUtils;
import com.example.ecommerce.frame.common.collection.MapUtils;
import com.example.ecommerce.frame.common.pojo.PageResult;
import com.example.ecommerce.frame.common.pojo.Pair;
import com.example.ecommerce.frame.test.AssertUtils;
import com.example.ecommerce.frame.test.RandomUtils;
import com.example.ecommerce.product.controller.comment.vo.PagingProductCommentReqVO;
import com.example.ecommerce.product.controller.comment.vo.ProductCommentCreateReqVO;
import com.example.ecommerce.product.controller.comment.vo.ProductCommentUpdateReqVO;
import com.example.ecommerce.product.dal.dataobject.comment.ProductComment;
import com.example.ecommerce.product.dal.dataobject.properties.ProductProperty;
import com.example.ecommerce.product.dal.dataobject.sku.ProductSku;
import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.product.dal.repository.comment.ProductCommentRepository;
import com.example.ecommerce.product.dal.repository.property.ProductPropertyRepository;
import com.example.ecommerce.product.dal.repository.sku.ProductSkuRepository;
import com.example.ecommerce.product.dal.repository.spu.ProductSpuRepository;
import com.example.ecommerce.system.dal.dataobject.user.UserMember;
import com.example.ecommerce.system.dal.repository.user.UserMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Import(ProductCommentServiceImpl.class)
class ProductCommentServiceTest extends TestBase {

    @Autowired private UserMemberRepository userMemberRepository;
    @Autowired private ProductSkuRepository productSkuRepository;
    @Autowired private ProductSpuRepository productSpuRepository;
    @Autowired private ProductCommentRepository productCommentRepository;
    @Autowired private ProductCommentService productCommentService;
    @Autowired private ProductPropertyRepository productPropertyRepository;
    @Test
    void testCreateProductComment_success() {
        UserMember userMember = random0().get(0);
        ProductSpu spu = random1().get(0);
        ProductSku sku = random2(spu).get(0);
        List<ProductProperty> productProperties = random3();
        ProductCommentCreateReqVO req = RandomUtils.randomPojo(ProductCommentCreateReqVO.class, p -> {
            p.setReplyCommentId(null); p.setUserMemberId(userMember.getId());
            p.setProductSkuId(sku.getId()); p.setProductSpuId(spu.getId());
            p.setMapProperties(MapUtils.convertToMap(CollUtils.convertList(productProperties, s -> new Pair<>(s.getId(), RandomUtils.randomString()))));
        });

        ProductComment res = this.productCommentService.createProductComment(req, null);

        AssertUtils.assertPojoEquals(userMember, res.getUserMember());
        AssertUtils.assertPojoEquals(spu, res.getProductSpu());
        AssertUtils.assertPojoEquals(sku, res.getProductSku(), "productSpu");
        AssertUtils.assertPojoEquals(res, req);

        List<ProductProperty> list = res.getProductCommentEvaluations().stream().map(eval -> {
            return eval.getProductProperty();
        }).collect(Collectors.toList());

        Assertions.assertEquals(list.size(), productProperties.size());

    }

    @Test
    void testUpdateComment_success() {
        ProductComment pc = random(random0().get(0), random1().get(0)).get(0);
        List<ProductProperty> productProperties = random3();
        ProductCommentUpdateReqVO req = RandomUtils.randomPojo(ProductCommentUpdateReqVO.class, p -> {
            p.setId(pc.getId());
            p.setMapProperties(MapUtils.convertToMap(CollUtils.convertList(productProperties, s -> new Pair<>(s.getId(), RandomUtils.randomString()))));

        });
        this.productCommentService.updateProductComment(req, null);
        ProductComment productComment = this.productCommentRepository.findById(pc.getId()).get();
        AssertUtils.assertPojoEquals(productComment, req);
        Assertions.assertEquals(productComment.getProductCommentEvaluations().size(), productProperties.size());

    }

    @Test
    void testGetPageCommentBySpuId_success() {
        ProductSpu spu = this.random1().get(0);
        UserMember userMember = this.random0().get(0);
        random(userMember, spu); //5
        random(userMember, spu); //5
        random(userMember, spu); //5
        PagingProductCommentReqVO reqVO = RandomUtils.randomPojo(PagingProductCommentReqVO.class, s -> {
            s.setProductSpuId(spu.getId()); s.setCurrentPage(1);
        });
        PageResult<ProductComment> pageResult = this.productCommentService.getListProductCommentByProductSpu(reqVO);

        Assertions.assertEquals(pageResult.getList().size(), 10);
        Assertions.assertEquals(pageResult.getCurrentPage(), 1);
        Assertions.assertEquals(pageResult.getTotalPage(), 2);

    }


    public List<ProductComment> random(UserMember userMember, ProductSpu spu) {
        List<ProductComment> productComments = RandomUtils.randomList(ProductComment.class, p -> {
            p.setProductSpu(spu);p.setUserMember(userMember);
            p.setProductSku(random2(spu).get(0));p.setId(null);
            p.setReplyProductComment(null);p.setProductCommentEvaluations(null);
            p.setProductCommentFavorites(null);p.setMediaList(null);
        });
        this.productCommentRepository.saveAll(productComments);
        return productComments;
    }
    public List<UserMember> random0() {
        List<UserMember> userMembers = RandomUtils.randomList(UserMember.class, s -> {
            s.setId(null);
        });
        this.userMemberRepository.saveAll(userMembers);
        return userMembers;
    }
    public List<ProductSpu> random1() {
        List<ProductSpu> productSpus = RandomUtils.randomList(ProductSpu.class, s -> {
            s.setId(null);s.setProductSkus(Collections.emptySet());
            s.setProductBrand(null); s.setSeller(null);s.setProductCategory(null);
            s.setProductSpuDetails(Collections.emptyList());
        });
        this.productSpuRepository.saveAll(productSpus);
        return productSpus;
    }
    public List<ProductSku> random2(ProductSpu spu) {
        List<ProductSku> productSkus = RandomUtils.randomList(ProductSku.class, s -> {
            s.setProductSkuProperties(Collections.emptySet());
            s.setProductSpu(spu);
            s.setId(null);
        });
        this.productSkuRepository.saveAll(productSkus);
        return productSkus;
    }
    public List<ProductProperty> random3() {
        List<ProductProperty> re = RandomUtils.randomList(ProductProperty.class, s -> {
            s.setId(null);
        });
        this.productPropertyRepository.saveAll(re);
        return re
                ;
    }
}
