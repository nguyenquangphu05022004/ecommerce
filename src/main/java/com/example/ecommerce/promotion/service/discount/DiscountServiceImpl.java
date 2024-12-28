package com.example.ecommerce.promotion.service.discount;

import com.example.ecommerce.product.dal.dataobject.spu.ProductSpu;
import com.example.ecommerce.promotion.controller.admin.discount.vo.self.DiscountCreateReqVO;
import com.example.ecommerce.promotion.dal.dataobject.discount.Discount;
import com.example.ecommerce.promotion.dal.dataobject.discount.DiscountActivity;
import com.example.ecommerce.promotion.dal.repo.discount.DiscountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ecommerce.frame.common.exception.utils.ServiceExceptionUtils.exception;
import static com.example.ecommerce.promotion.constants.ErrorConstants.DISCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService{
    private final DiscountRepository discountRepository;
    @Override
    public Discount createDiscount(DiscountCreateReqVO reqVO) {

        Discount discount = Discount.builder()
                .discountActivity(DiscountActivity.builder().id(reqVO.getDiscountActivityId()).build())
                .productSpu(ProductSpu.builder().id(reqVO.getProductSpuId()).build())
                .discountType(reqVO.getDiscountType())
                .discountAmount(reqVO.getDiscountAmount())
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

    @Override
    public void deleteById(Long id) {
        this.discountRepository.deleteById(id);
    }

    @Override
    public Discount getDiscountById(Long id) {
        return this.discountRepository.findById(id)
                .orElseThrow(() -> exception(DISCOUNT_NOT_FOUND));
    }

    @Override
    public Discount updateDiscount(DiscountCreateReqVO req) {
        Discount discount = getDiscountById(req.getId()).toBuilder()
                .discountAmount(req.getDiscountAmount())
                .discountType(req.getDiscountType())
                .productSpu(ProductSpu.builder().id(req.getProductSpuId()).build())
                .discountActivity(DiscountActivity.builder().id(req.getDiscountActivityId()).build())
                .build();
        this.discountRepository.save(discount);
        return discount;
    }
}
