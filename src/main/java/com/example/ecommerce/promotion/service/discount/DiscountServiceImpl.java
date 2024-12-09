package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.promotion.controller.discount.vo.DiscountCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import com.example.ecommerce.promotion.dal.repo.discount.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService{
    private final DiscountRepository discountRepository;
    @Override
    public Discount createDiscount(DiscountCreateReqVO reqVO) {
        Discount discount = Discount.builder().discountPercent(reqVO.getDiscountPercent())
                .discountActivity(DiscountActivity.builder().id(reqVO.getDiscountActivityId()).build())
                .productSpu(ProductSpu.builder().id(reqVO.getProductSpuId()).build())
                .discountType(reqVO.getDiscountType())
                .discountPrice(reqVO.getDiscountPrice())
                .build();
        this.discountRepository.save(discount);
        return discount;
    }

    @Override
    public List<Discount> getDiscountBySpuId(Long spuId) {
        return this.discountRepository.findAllByProductSpuId(spuId);
    }

    @Override
    public List<Discount> getListDiscount(Long userId) {
        return this.discountRepository.findAllByCreatedBy(userId);
    }
}
